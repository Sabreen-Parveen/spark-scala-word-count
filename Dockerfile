#------------------------
# SPARK 2.1.0 SCALA
#------------------------

FROM hseeberger/scala-sbt:8u222_1.3.5_2.13.1

ENV SPARK_VERSION 2.1.0

RUN curl https://archive.apache.org/dist/spark/spark-$SPARK_VERSION/spark-$SPARK_VERSION-bin-hadoop2.7.tgz | tar -xz -C /opt
RUN cd /opt && ln -s ./spark-$SPARK_VERSION-bin-hadoop2.7 spark

COPY . /spark-word-count

ENV SPARK_HOME /opt/spark
ENV PATH $SPARK_HOME/bin:$PATH

CMD ["bash"]