FROM quay.io/wildfly/wildfly
RUN /opt/jboss/wildfly/bin/add-user.sh -up mgmt-users.properties admin admin123 --silent
CMD ["/opt/jboss/wildfly/bin/standalone.sh", "-b", "0.0.0.0", "-bmanagement", "0.0.0.0"]
