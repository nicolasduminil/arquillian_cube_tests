echo ">>>>>>>>>>>>>>>>>>>>>>"
JAVA_HOME=$(dirname $(dirname $(readlink -f /etc/alternatives/java)))
echo ">>>>>>>>>>>>>>>> JAVA_HOME: $JAVA_HOME"
sudo keytool -delete -alias s1as -cacerts
sudo keytool -importcert -alias s1as -file s1as.cer -cacerts
