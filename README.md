vaadin-style-combine-maven-plugin
=================================

Maven plugin for vombining multiple css files into a single styles.css

This maven plugin will combine style.css files so that only one request will be made.
It works in a limited amount of scenarios, similar to the way Vaadin does it.
It requires a theme to have a "styles.css" like this:

@import "../reindeer/styles.css";
@import "general/general.css";
@import "header/header.css";


After it has run, it will generate a folder "generated-vaadin-styles" in "target".

To use this one instead of the original, use the following maven-war-plugin config:

				<groupId>org.apache.maven.plugins</groupId>
				<artifactId>maven-war-plugin</artifactId>
				<configuration>
					<excludes>VAADIN/themes/theme/styles.css</excludes>
					<packagingExcludes>WEB-INF/web.xml</packagingExcludes>
					<webResources>
            			<resource>
            				<directory>${project.build.directory}/generated-vaadin-styles/</directory>
            			</resource>
            		</webResources>
  				</configuration>
			
To run the combine plugin:

			<plugin>
				<groupId>de.codecentric.vaadin</groupId>
				<artifactId>vaadin-style-combine-maven-plugin</artifactId>
				<version>1.0</version>
				<configuration>
    				<theme>centerdevice</theme>
  				</configuration>
				<executions>
					<execution>
						<phase>compile</phase>
						<goals>
							<goal>combine</goal>
						</goals>
					</execution>
				</executions>
			</plugin>