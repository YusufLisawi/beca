<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords" content="struts2, bootstrap, plugin, grh, about, ntt data" />
    <meta name="description" content="About - Gestion des Ressources Humaines - NTT Data" />
    <title>About - Gestion des Ressources Humaines - NTT Data</title>

    <sb:head includeScripts="false"/>
</head>
<body>

<s:include value="includes/topMenu.jsp">
    <s:param name="active">about</s:param>
</s:include>


<div class="col-md-12">

    <h1>About Struts2 Gestion des Ressources Humaines</h1>

    <h2>Project</h2>
    <ul>
        <li><a href="https://github.com/LikToGithubRepository/">Project @ Github</a></li>
        <li><a href="https://LinkForDirectDownload/">Download</a></li>
    </ul>

    <h2> Maven Dependencies</h2>
    <pre>
&lt;dependencies&gt;
	  &lt;!-- https://mvnrepository.com/artifact/org.hibernate/hibernate-core --&gt;
	&lt;dependency&gt;
	    &lt;groupId&gt;org.hibernate&lt;/groupId&gt;
	    &lt;artifactId&gt;hibernate-core&lt;/artifactId&gt;
	    &lt;version&gt;6.2.22.Final&lt;/version&gt;
	&lt;/dependency&gt;
	
	&lt;!-- https://mvnrepository.com/artifact/mysql/mysql-connector-java --&gt;
	&lt;dependency&gt;
	    &lt;groupId&gt;mysql&lt;/groupId&gt;
	    &lt;artifactId&gt;mysql-connector-java&lt;/artifactId&gt;
	    &lt;version&gt;8.0.33&lt;/version&gt;
	&lt;/dependency&gt;
	
	&lt;!-- https://mvnrepository.com/artifact/org.apache.struts/struts2-core --&gt;
	&lt;dependency&gt;
	    &lt;groupId&gt;org.apache.struts&lt;/groupId&gt;
	    &lt;artifactId&gt;struts2-core&lt;/artifactId&gt;
	    &lt;version&gt;6.3.0.2&lt;/version&gt;
	&lt;/dependency&gt;
	
	&lt;!-- https://mvnrepository.com/artifact/com.jgeppert.struts2.jquery/struts2-jquery-plugin --&gt;
	&lt;dependency&gt;
	    &lt;groupId&gt;com.jgeppert.struts2.jquery&lt;/groupId&gt;
	    &lt;artifactId&gt;struts2-jquery-plugin&lt;/artifactId&gt;
	    &lt;version&gt;5.0.3&lt;/version&gt;
	&lt;/dependency&gt;
	
	&lt;!-- https://mvnrepository.com/artifact/com.jgeppert.struts2.bootstrap/struts2-bootstrap-plugin --&gt;
	&lt;dependency&gt;
	    &lt;groupId&gt;com.jgeppert.struts2.bootstrap&lt;/groupId&gt;
	    &lt;artifactId&gt;struts2-bootstrap-plugin&lt;/artifactId&gt;
	    &lt;version&gt;5.0.3&lt;/version&gt;
	&lt;/dependency&gt;
	
	&lt;!-- https://mvnrepository.com/artifact/com.jgeppert.struts2.jquery/struts2-jquery-grid-plugin --&gt;
	&lt;dependency&gt;
	    &lt;groupId&gt;com.jgeppert.struts2.jquery&lt;/groupId&gt;
	    &lt;artifactId&gt;struts2-jquery-grid-plugin&lt;/artifactId&gt;
	    &lt;version&gt;5.0.3&lt;/version&gt;
	&lt;/dependency&gt;
	
	&lt;!-- https://mvnrepository.com/artifact/org.apache.struts/struts2-json-plugin --&gt;
	&lt;dependency&gt;
	    &lt;groupId&gt;org.apache.struts&lt;/groupId&gt;
	    &lt;artifactId&gt;struts2-json-plugin&lt;/artifactId&gt;
	    &lt;version&gt;6.3.0&lt;/version&gt;
	&lt;/dependency&gt;
	
	&lt;!-- https://mvnrepository.com/artifact/org.apache.struts/struts2-convention-plugin --&gt;
	&lt;dependency&gt;
	    &lt;groupId&gt;org.apache.struts&lt;/groupId&gt;
	    &lt;artifactId&gt;struts2-convention-plugin&lt;/artifactId&gt;
	    &lt;version&gt;6.3.0&lt;/version&gt;
	&lt;/dependency&gt;

	&lt;!-- https://mvnrepository.com/artifact/org.apache.logging.log4j/log4j-core --&gt;
		&lt;dependency&gt;
		    &lt;groupId&gt;org.apache.logging.log4j&lt;/groupId&gt;
		    &lt;artifactId&gt;log4j-core&lt;/artifactId&gt;
		    &lt;version&gt;2.20.0&lt;/version&gt;
		&lt;/dependency&gt;
  &lt;/dependencies&gt;
    </pre>


    <h2>Developer</h2>
    <ul>
        <li><a href="https://www.Yoursite.com">Developer Homepage and Contact</a></li>
        <li><a href="https://twitter.com/YourTwitterAcount">Twitter News</a></li>
    </ul>
</div>

    <s:include value="includes/footer.jsp" />
</body>
</html>