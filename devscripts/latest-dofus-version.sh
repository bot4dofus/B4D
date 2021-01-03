link=$(curl -s https://www.dofus.com/fr/mmorpg/actualites/maj/correctifs | grep '<a href="/fr/mmorpg/actualites/maj/' | grep 'correctif-' | head -n 1)
version=$(echo ${link#*'-correctif-'} | cut -d'"' -f 1)
echo ${version//-/.}
