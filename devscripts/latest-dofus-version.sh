link=$(curl -s https://www.dofus.com/fr/rss/changelog.xml | grep 'https://www.dofus.com/fr/mmorpg/actualites/maj/' -m 1)
version=$(echo ${link#*'-correctif-'} | cut -d'"' -f 1)
version=${version%]]></link>}
echo ${version//-/.}
