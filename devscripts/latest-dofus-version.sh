link=$(curl -s https://www.dofus.com/fr/mmorpg/actualites/maj/correctifs | grep '<a href="/fr/mmorpg/actualites/maj/' | grep 'correctif-' | head -n 1)
pre_version=${link#*'-correctif-'}
#end_index=$((${#pre_version}-2))
#version=${pre_version:0:${end_index}}
#echo ${version//-/.}
echo $pre_version
