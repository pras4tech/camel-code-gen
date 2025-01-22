#!/bin/bash

chmod -R 755 './converter.properties'
tempOrg="eprise"
tempApp="sapp"
tempModule="smod"
tempComponent="scomp"
file="./converter.properties"
filenew="./convertern.properties"

sed 's/\r$//' $file > $filenew

while IFS='=' read -r key value
do
	key=$(echo $key | tr '.' '_')
	eval ${key}=\${value}
	echo ${value}
done < "$filenew"

find . -type f -name '*.log' -delete
find . -type f -name '*.bak' -delete

if [ -d ./${portfolio}-${module} ]; then
  rm -rf ./${portfolio}-${module}
  echo "Folder './${portfolio}-${module}' removed."
else
  echo "Folder './${portfolio}-${module}' does not exist."
fi

cp -r ./template/$tempComponent ./${portfolio}-${module}

grep -r -i $tempOrg ${portfolio}-${module} > org-entries.log
grep -r -i $tempApp ${portfolio}-${module} > app-entries.log
grep -r -i $tempModule ${portfolio}-${module} > module-entries.log
grep -r -i $tempComponent ${portfolio}-${module} > component-entries.log

i=0;
for file in $(grep -l -R $tempOrg ${portfolio}-${module})
do 
	sed -e "s/$tempOrg/${organization}/ig" $file > tempfile.tmp
	mv tempfile.tmp $file
	let i++
	echo "Modified: " $file
done

i=0;
for file in $(grep -l -R $tempApp ${portfolio}-${module})
do 
	sed -e "s/$tempApp/${portfolio}/ig" $file > tempfile.tmp
	mv tempfile.tmp $file
	let i++
	echo "Modified: " $file
done

i=0;
for file in $(grep -l -R $tempModule ${portfolio}-${module})
do 
	sed -e "s/$tempModule/${module}/ig" $file > tempfile.tmp
	mv tempfile.tmp $file
	let i++
	echo "Modified: " $file
done

i=0;
for file in $(grep -l -R $tempComponent ${portfolio}-${module})
do 
	sed -e "s/$tempComponent/${service}/ig" $file > tempfile.tmp
	mv tempfile.tmp $file
	let i++
	echo "Modified: " $file
done

mv "${portfolio}-${module}/src/main/java/com/$tempOrg/$tempApp/$tempModule/$tempComponent/ScompApplication.java" "${portfolio}-${module}/src/main/java/com/$tempOrg/$tempApp/$tempModule/$tempComponent/${service}Application.java"

mv "${portfolio}-${module}/src/main/java/com/$tempOrg/$tempApp/$tempModule/$tempComponent" "${portfolio}-${module}/src/main/java/com/$tempOrg/$tempApp/$tempModule/${service}"

mv "${portfolio}-${module}/src/main/java/com/$tempOrg/$tempApp/$tempModule" "${portfolio}-${module}/src/main/java/com/$tempOrg/$tempApp/${module}"

mv "${portfolio}-${module}/src/main/java/com/$tempOrg/$tempApp" "${portfolio}-${module}/src/main/java/com/$tempOrg/${portfolio}"

mv "${portfolio}-${module}/src/main/java/com/$tempOrg" "${portfolio}-${module}/src/main/java/com/${organization}"

