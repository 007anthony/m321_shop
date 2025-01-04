envVariables=$(printenv | grep -E "^.+_FILE")


for env in $envVariables; do
  variableName=$(echo $env | cut -d'=' -f 1)
  filePath=$(echo $env | cut -d'=' -f 2)


  if [ -f $filePath ]; then
    echo $variableName
    export $(echo $variableName | sed s/_FILE//g)="$(cat $filePath)"
    env | grep TEST
  else
    echo "File \"$filePath\" is not a file" >&2
  fi
done

mvn spring-boot:run