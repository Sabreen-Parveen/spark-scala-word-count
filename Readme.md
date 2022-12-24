This project is a demo on how to write, compile, export, and run a spark word count job via spark scala with a docker container. In this version of WordCount, the goal is to learn the distribution of letters in the most popular words in a file.

## Execution


### Prerequisites:

Install docker

On linux:

STEP 1) 

```$ cd spark-scala-word-count```


STEP 2) docker build 

```$ docker build . -t spark_env```


STEP 3) ONE COMMAND : run the docker env and sbt compile and sbt run and assembly once 


```
$ docker run  --mount \
type=bind,\
source="$(pwd)"/.,\
target=/spark-word-count \
-i -t spark_env \
/bin/bash  -c "cd ../spark-word-count && sbt clean compile && sbt run && sbt assembly && spark-submit /spark-word-count/target/scala-2.11/spark-scala-word-count-assembly-1.0.jar"
```

For windows:
Open project folder in the cmd and run:

```$ docker build . -t spark_env```

```
$ docker run  --mount \
type=bind,\
source="$(pwd)"/.,\
target=/spark-word-count \
-i -t spark_env \
/bin/bash  -c "cd ../spark-word-count && sbt clean compile && sbt run && sbt assembly && spark-submit /spark-word-count/target/scala-2.11/spark-scala-word-count-assembly-1.0.jar"
```

