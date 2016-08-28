Learning on building this application
MVN command to build and deploy in a tomcat 7 server: mvn clean install tomcat7:run

Dependency entries in pom.xml
------------------------------
Servlet 3.0 introduces a ServletContainerInitializer interface whose implementing classes are notified by the container
about webapp startup events. Spring 3.1 uses this in its WebApplicationInitializer interface, which is the hook through
which you can set up ApplicationContext without using the web.xml. Combine that with Spring’s Java-based configuration,
and you can successfully ditch XML files entirely.

<dependency>
	<groupId>javax.servlet</groupId>
	<artifactId>javax.servlet-api</artifactId>
	<version>3.0.1</version>
	<scope>provided</scope>
</dependency> 

<!--adding spring mvc is easy -->
<dependency>
<groupId>org.springframework</groupId>
<artifactId>spring-webmvc</artifactId>
<version>3.1.1.RELEASE</version>
</dependency>

<!-- Add this tomcat plugin-->
  <plugin>
	<groupId>org.apache.tomcat.maven</groupId>
	<artifactId>tomcat7-maven-plugin</artifactId>
	 <version>2.0</version>
	   <configuration>
			<url>http://localhost:8080/spring</url>
			<server>my-tomcat</server>
			<path>/spring</path>
	 </configuration>
</plugin>	

<!-- When using xml-less approach, you need to disable Maven's build failure (<failOnMissingWebXml>false</failOnMissingWebXml>)
about missing web.xml -->
<plugin>
	<groupId>org.apache.maven.plugins</groupId>
	<artifactId>maven-war-plugin</artifactId>
	<configuration>
	<failOnMissingWebXml>false</failOnMissingWebXml>
	</configuration>
</plugin>

Following instruction are added but not sure does this entries really required

In your maven settings.xml
----------------------------------
<server>
   <id>my-tomcat</id>
   <username>manager</username>
   <password>managerPwd</password>
</server>

In your tomcat's apache-tomcat-7.0.33\conf\tomcat-users.xml file
----------------------------------------------------
<!-- Role to manage WAR files via HTML /manager. The name should be as is! -->
<role rolename="manager-gui"/>
<!-- Role to manage WAR files via script like Maven. The name should be as is! -->
<role rolename="manager-script"/>

<!-- One user cannot have manager-gui and manager-script roles -->
<user username="managerGui" password="managerPwd" roles="manager-gui"/>
<user username="manager" password="managerPwd" roles="manager-script"/>

References:
http://zeroturnaround.com/rebellabs/your-next-java-web-app-less-xml-no-long-restarts-fewer-hassles-part-1/#!/
http://www.rockhoppertech.com/blog/spring-mvc-configuration-without-xml/

To enable the MVC 
use   @EnableWebMvc To achieve the same in XML use <mvc:annotation-driven />

@EnableWebMvc - Also enables the following
1) Spring 3 style type conversion through a ConversionService instance in addition to the JavaBeans PropertyEditors used for Data Binding.
2) Support for formatting Number fields using the @NumberFormat annotation through the ConversionService.
3) Support for formatting Date, Calendar, Long, and Joda Time fields using the @DateTimeFormat annotation.
4) Support for validating @Controller inputs with @Valid, if a JSR-303 Provider is present on the classpath.
5) HttpMessageConverter support for @RequestBody method parameters and @ResponseBody method return values from @RequestMapping or @ExceptionHandler methods.
This is the complete list of HttpMessageConverters set up by mvc:annotation-driven: 
a) Jaxb2RootElementHttpMessageConverter converts Java objects to/from XML — added if JAXB2 is present on the classpath.
b) MappingJackson2HttpMessageConverter (or MappingJacksonHttpMessageConverter) converts to/from JSON — added if Jackson 2 (or Jackson) is present on the classpath.

@Profile to enable different database (e.g) Unit testing with: HSQLDB and production with: Oracle:
In this project HSQL database has been used for Unit testing and Oracle database has been used for   
Please take a look at @Profile("HSQL_EMBEDDED") in JPAHSQLEmbeddedConfig.java and @ActiveProfiles("HSQL_EMBEDDED") in JPAHsqlIntegrationTest.java

Plugin:
----------------------------------------------------------------------------------------------------------
Added couple of plugin to 
1)Existing dependencies, dependency tree and plugin used in the project.
2)Generate checkstyle, Junit execution status, code coverage, PMD report available at springlatest\target\site\index.html
more to explore on this one
Command to generate all the above report and project details: mvn site

