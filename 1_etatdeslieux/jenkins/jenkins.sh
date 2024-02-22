#!/bin/sh

export JAVA_HOME=/usr/lib/jvm/java-1.17.0-openjdk-amd64

export JENKINS_BASE=/home/dthibau/Formations/bceao-cursus/github/1_etatdeslieux/jenkins

export JENKINS_HOME=${JENKINS_BASE}/.jenkins
java -Dhudson.plugins.git.GitSCM.ALLOW_LOCAL_CHECKOUT=true -Xmx1024m -jar ${JENKINS_BASE}/jenkins.war --httpPort=8082
