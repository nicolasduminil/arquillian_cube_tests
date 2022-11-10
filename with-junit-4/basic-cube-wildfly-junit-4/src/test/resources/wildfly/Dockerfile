FROM quay.io/wildfly/wildfly
ENV ADMIN_USER admin
ENV ADMIN_PASSWORD admin123
RUN /opt/jboss/wildfly/bin/add-user.sh -u $ADMIN_USER -p $ADMIN_PASSWORD
ENV JAVA_OPTS "-Xms64m -Xmx1024m -Djava.net.preferIPv4Stack=true"
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
