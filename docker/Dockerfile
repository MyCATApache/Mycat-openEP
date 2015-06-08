# Mycat Openep Dockerfile

FROM centos-6.6:latest
MAINTAINER oznyang <oznyang@163.com>

ADD modules/ /tmp/_dt/
RUN sh /tmp/_dt/install.sh

VOLUME /opt/data-vol

#      ssh nginx tomcat zookeeper mysql mycat mycat-admin ice-registry ice-service supervisor addon-port1 port2 port3 port4 port5 port6 port7 port8 port9
EXPOSE 22  80    8080   2181      3306  8066  9066        4061         8888        9001       9101        9102  9103  9104  9105  9106  9107  9108  9109

CMD ["/usr/bin/supervisord"]