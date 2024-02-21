#! /bin/bash

# valeur par défaut en cas d'absence d'arguments 
# $0 <=> nom du script 
# $<i> <=> ième argument (i<10) 
# $# <=> nombre d'arguments 

# argument 1 <=> base de la recherche

DEFAULT_BASE_PATH=/usr
DEFAULT_SIZE_MIN=10k
DEFAULT_SIZE_GAL=25
DEFAULT_SIZE_IMAGE_GAL_MAX=200
DEFAULT_TYPE_IMAGE=png
DEFAULT_GALLERY_PATH=./galeries

#############
# Fonctions #
#############
enter2continue() {
	echo -n "Appuyez sur une touche pour continuer ..."
	read PAUSE
	clear
}

ask2continue() {
	echo -n "Voulez-vous continuer [Y-n] ? "
	read REPONSE
	if [ "$REPONSE" = "n" ] || [ "$REPONSE" = "N" ];
	then
		exit 
	fi
}

# Message d'aide
if [ "$1" = "-h" ]; then
  echo "./makeGalerie [rep base] [taille min] [taille galerie] [taille image galerie] [type image] [rep galerie]" 1>&2
  exit 1
fi

if [ "$1" = "-clear" ]; then

  echo -n "Voulez-vous supprimer le contenu de ./galeries ? (Images, html) [y-N] : "
  read REPONSE
  if [ "$REPONSE" = "y" ] || [ "$REPONSE" = "Y" ]; then
    rm -rf ./galeries/*
    exit 1
  else
    exit 1
  fi
fi

# Répertoire de base de la recherche

if [ $# -ge 1 ];  then
	BASE_PATH=$1
else

  echo -n "Entrer un répertoire de base de la recherche [/usr] : "
  read REPONSE
  if [ -z $REPONSE ]; then
    BASE_PATH=/usr
  else
    BASE_PATH=$REPONSE
  fi

fi

# vérifier que le répertoire existe

while [ ! -d $BASE_PATH ]
do
  echo "Le répertoire $BASE_PATH n'existe pas" 1>&2
  # questionner l'utilisateur

  enter2continue

  echo -n "Entrer un répertoire valide [/usr] : "
  read REPONSE
	if [ -z $REPONSE ]; then
	  BASE_PATH=/usr
  else
		BASE_PATH=$REPONSE
	fi
    # exit 1 # code d'erreur
done

echo Utilisation de $BASE_PATH
ask2continue

# vérifier que le répertoire est accessible en lecture

if [ ! -r $BASE_PATH ]
then
  echo "Le répertoire $BASE_PATH n'est pas accessible en lecture" 1>&2
  exit 2
fi

# Taille minimale des fichiers à rechercher

if [ $# -ge 2 ];  then
	SIZE_MIN=$2
else

  while [ -z $SIZE_MIN ]
  do

    echo "La taille minimale doit être exprimée en octets (o), kilo-octets (k), méga-octets (M) ou giga-octets (G)"

    echo -n "Entrer la taille minimale des images à rechercher [10k] : "
    read REPONSE
    if [ -z $REPONSE ]; then
      SIZE_MIN=10k
    else
      SIZE_MIN=$REPONSE
    fi
  done

fi

echo "Taille minimale des images : $SIZE_MIN"

ask2continue

# Taille des galeries à produire

if [ $# -ge 3 ];  then
  SIZE_GAL=$3
else

  while [ -z $SIZE_GAL ]
  do

    echo "La taille des galeries doit être spécifiée"

    enter2continue

    echo -n "Entrer la taille des galeries à produire [25] : "
    read REPONSE
    if [ -z $REPONSE ]; then
      SIZE_GAL=25
    else
      SIZE_GAL=$REPONSE
    fi

    # Vérifier que la taille est un nombre et non nulle et non négative
    if ! [[ $SIZE_GAL =~ ^[0-9]+$ ]] || [ $SIZE_GAL -le 0 ]; then
      echo "La taille des galeries doit être un nombre entier positif"
      SIZE_GAL=
    fi

  done

fi

echo "Taille des galeries : $SIZE_GAL"

ask2continue

# Taille maximale (Largeur x Hauteur) des images dans les galeries

if [ $# -ge 4 ];  then
  SIZE_IMAGE_GAL_MAX=$4
else

  while [ -z $SIZE_IMAGE_GAL_MAX ]
  do

    echo "La taille maximale des images doit être exprimée en pixels"

    enter2continue

    echo -n "Entrer la taille maximale des images dans les galeries [200] : "
    read REPONSE
    if [ -z $REPONSE ]; then
      SIZE_IMAGE_GAL_MAX=200
    else
      SIZE_IMAGE_GAL_MAX=$REPONSE
    fi

    # Vérifier que la taille est un nombre et non nulle et non négative
    if ! [[ $SIZE_IMAGE_GAL_MAX =~ ^[0-9]+$ ]] || [ $SIZE_IMAGE_GAL_MAX -le 0 ]; then
      echo "La taille maximale des images doit être un nombre entier positif"
      SIZE_IMAGE_GAL_MAX=
    fi

  done

fi

echo "Taille maximale des images dans les galeries : $SIZE_IMAGE_GAL_MAX x $SIZE_IMAGE_GAL_MAX"

ask2continue

# Type des images à rechercher

if [ $# -ge 5 ];  then
  TYPE_IMAGE=$5
else

  while [ -z $TYPE_IMAGE ]
  do

    echo "Le type des images doit être spécifié"

    enter2continue

    echo -n "Entrer le type des images à rechercher [png] : "
    read REPONSE
    if [ -z $REPONSE ]; then
      TYPE_IMAGE=png
    else
      TYPE_IMAGE=$REPONSE
    fi

    # Vérifier que le type est un type d'image
    if ! [[ $TYPE_IMAGE =~ ^(png|jpg|jpeg|gif)$ ]]; then
      echo "Le type des images doit être png, jpg, jpeg ou gif"
      TYPE_IMAGE=
    fi

  done

fi

ask2continue

# Localisation du répertoire où créer les fichier de galeries

if [ $# -ge 6 ];  then
  GALLERY_PATH=$6
else

  while [ -z $GALLERY_PATH ]
  do

    echo "Le répertoire où créer les fichiers de galeries doit être spécifié"

    enter2continue

    echo -n "Entrer le répertoire où créer les fichiers de galeries [./galerie] : "
    read REPONSE
    if [ -z $REPONSE ]; then
      GALLERY_PATH=./galeries
    else
      GALLERY_PATH=$REPONSE
    fi

    # Vérifier que le répertoire existe
    if [ ! -d $GALLERY_PATH ]; then
      echo "Le répertoire $GALLERY_PATH n'existe pas"

      echo -n "Voulez-vous le créer [Y-n] ? "
      read REPONSE
      if [ "$REPONSE" = "n" ] || [ "$REPONSE" = "N" ]; then
        GALLERY_PATH=
      else
        mkdir -p $GALLERY_PATH
      fi

    fi

  done

fi

echo "Voulez-vous supprimer le contenu de $GALLERY_PATH ? (Images, html) [y-N] : "
read REPONSE
if [ "$REPONSE" = "y" ] || [ "$REPONSE" = "Y" ]; then
  rm -rf $GALLERY_PATH/*
  echo "Contenu de $GALLERY_PATH supprimé"
fi


echo "Voulez-vous commencer la recherche des images dans $BASE_PATH ? "

ask2continue

cd $GALLERY_PATH

# Recherche des images dans le répertoire de base

find $BASE_PATH -type f -size +$SIZE_MIN -name "*.$TYPE_IMAGE" -exec ln -s {} \; 2>/dev/null

# Message nombre d'images trouvées

NB_IMAGES=$(find . -type l | wc -l)

echo "Nombre d'images trouvées : $NB_IMAGES"

# Création des galeries

if [ ! -d html ]; then
  mkdir html
fi

echo '<div class="cadreP">' > html/galerie.html

find . -type l -exec echo '<div class="cadre"><img src="../{}" /><p>{}</p></div>' \; >> html/galerie.html

echo '</div>' >> html/galerie.html

echo "Galerie compléte créée dans $GALLERY_PATH/html/galerie.html"

# Créations des galeries en fonction du nombre d'images par pages
split -d -l$SIZE_GAL -a3 html/galerie.html html/gal
find html/gal??? -exec mv {} {}.html \;

echo "Les galeries ont été crées"

# Fichier index.html
#Chaque image devra être centrée dans un cadre comportant également le nom de l’image, en bas du cadre. Les cadres devront faire 20 pixels de plus en largeur que la taille des images, et 50px de plus en hauteur.
echo "<style>
  .cadre {
    display: inline-block;
    min-width: `expr $SIZE_IMAGE_GAL_MAX + 20`px;
    min-height: `expr $SIZE_IMAGE_GAL_MAX + 50`px;
    max-width: `expr $SIZE_IMAGE_GAL_MAX + 20`px;
    max-height: `expr $SIZE_IMAGE_GAL_MAX + 50`px;
    border: 1px solid black;
    text-align: center;
    float: left;
    margin:5px;
  }
  .cadre img {
    max-width: ${SIZE_IMAGE_GAL_MAX}px;
    max-height: ${SIZE_IMAGE_GAL_MAX}px;
  }
  .cadre p {
    margin: 0;
  }
  a {
    text-decoration: none;
    color: black;
  }
  a:hover {
    text-decoration: underline;
    color: blue;
  }
  hr {
    margin: 20px 0;
  }
  </style>" > html/index.html

NUM_GAL=1
for FIC in $(ls html/gal???.html)
do
  echo "Traitement de $FIC"
  echo "Galerie $NUM_GAL"
  echo "<a href=../$FIC>Galerie $NUM_GAL </a>" >> html/index.html
  NUM_GAL=`expr $NUM_GAL + 1`
done

echo "<hr />" >> html/index.html
echo "Création de l'index des galeries"

# Ajout des style et des index aux galeries
for FIC in $(ls html/gal???.html)
do
  cat html/index.html $FIC > aux
  mv aux $FIC
done

echo "Les index ont été ajoutés aux galeries"

exit

# Desnyder Rémi
# LE2 - B1
# TNE1 - SHA