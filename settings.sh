JAVA_HOME=$(dirname $(dirname $(readlink -f /etc/alternatives/java)))
docker exec -ti payara5 /bin/bash -c "keytool -storepass changeit -export -alias s1as -keystore ./appserver/glassfish/domains/domain1/config/cacerts.jks -rfc -file s1as.cer"
docker cp payara5:/opt/payara/s1as.cer .
