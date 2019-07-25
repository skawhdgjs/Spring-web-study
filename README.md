# Spring-web-study

### Print project tress
./gradlew -q projects

### Execute multi project task
./gradlew [:]<project name>:<task name>

### multi Project layout
계층형 :
    루트 프로젝트가 상위 / 자식 프로젝트가 하위 
    include '<project name>'

단층형 :
    루트 프로젝트와 자식프로젝트가 같은 계층으로 구성
    includeFlat '<project name>'
    
