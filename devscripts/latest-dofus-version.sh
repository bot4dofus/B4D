link=$(curl -s https://www.dofus.com/fr/mmorpg/actualites/maj/correctifs | grep '<a href="/fr/mmorpg/actualites/maj/' | grep 'correctif-' | head -n 1)
pre_version=${link#*'-correctif-'}
version=${pre_version:0:(${#pre_version}-2)}
echo ${version//-/.}
