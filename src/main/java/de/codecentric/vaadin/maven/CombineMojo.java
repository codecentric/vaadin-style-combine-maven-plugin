package de.codecentric.vaadin.maven;

import java.io.File;
import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;

@Mojo(name = "combine", defaultPhase = LifecyclePhase.COMPILE, threadSafe = true, requiresDependencyResolution = ResolutionScope.RUNTIME)
public class CombineMojo extends AbstractMojo {

    @Parameter(required = true)
    private String theme;

    /**
     * Single directory for extra files to include in the WAR. This is where you place your JSP
     * files.
     */
    @Parameter(defaultValue = "${basedir}/src/main/webapp", required = true)
    private File warSourceDirectory;

    /**
     * The directory where the combined style is built.
     */
    @Parameter(defaultValue = "${project.build.directory}/generated-vaadin-styles", required = true)
    private File targetDirectory;

    public void execute() throws MojoExecutionException {
	try {
	    ThemeCombiner compiler = new ThemeCombiner(getLog(), warSourceDirectory,
		    targetDirectory);
	    compiler.combineTheme(theme);
	} catch (IOException e) {
	    getLog().error(e);
	}
    }
}