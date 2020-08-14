#!/bin/bash
echo 'downloading open api generator'
#wget https://repo1.maven.org/maven2/org/openapitools/openapi-generator-cli/5.0.0-beta/openapi-generator-cli-5.0.0-beta.jar -O openapi-generator-cli.jar
mkdir -p ~/bin/openapitools
curl https://raw.githubusercontent.com/OpenAPITools/openapi-generator/master/bin/utils/openapi-generator-cli.sh > ~/bin/openapitools/openapi-generator-cli
chmod u+x ~/bin/openapitools/openapi-generator-cli
export PATH=$PATH:~/bin/openapitools/
#echo 'installing clang-format...'
#brew install clang-format

#export JAVA_POST_PROCESS_FILE="/usr/local/bin/clang-format -i"
echo 'generating server stubs...'
#~/bin/openapitools/openapi-generator-cli generate -i ./yanis.yaml -g jaxrs-jersey -p com.yahoo.yanis --enable-post-process-file

~/bin/openapitools/openapi-generator-cli generate -i ./yanis.yaml -g jaxrs-jersey -p com.yahoo.yanis --generate-alias-as-model
