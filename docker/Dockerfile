# Mycat Openep Dockerfile

FROM index.alauda.cn/linzhiqiang/centos6
MAINTAINER oznyang <oznyang@163.com>

ADD modules/ /tmp/_dt/
RUN sh /tmp/_dt/install.sh

VOLUME /opt/data-vol

#      ssh nginx tomcat zookeeper mysql mycat mycat-admin ice-registry ice-service supervisor addon-port1 port2 port3
EXPOSE 2222  80    8080   2181      3306  8066  9066        4061         8888        9001       9101        9102  9103

CMD ["/usr/bin/supervisord"]