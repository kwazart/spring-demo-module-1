<h1>Module #1</h1>

<h3>Description</h3>
<p>It is an application for taking exams.</p>

<ol>
    <li>Xml-version for IoC-container (27.02.2022)</li>
    <li>Annotation-config-version for IoC-container (06.03.2022)</li>
    <li>Project was changed on Spring Boot version (10.03.2022)</li>
    <li>Added localization for exam (english and russian) (12.03.2022)</li>
</ol>

<h3>To Run:</h3>
<code>
mvn clean package
<br>
java -jar spring-boot-demo-0.0.1-SNAPSHOT.jar
</code>

<h3>How to use</h3>
<p>
For language choosing you may change only one property in application.yml (<code>main.locale</code>)<br>
Run the jar file. Enter your first and last name. Then ask at some questions (just follow instruction on screen)<br>
After answering you will see your result.<br>
</p>