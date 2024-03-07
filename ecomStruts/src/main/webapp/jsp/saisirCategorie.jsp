<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sx" uri="/struts-dojo-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head />
<sx:head  />
<sb:head  />

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name = "categorie.form.newCategorie.title" /></title>
</head>
<body>
    <center>
    <s:url var = "saisirCategorieEN" action="saisirCategorie"  >
         <s:param name = "request_locale" >en</s:param>
      </s:url>
      
      <s:url var = "saisirCategorieES" action="saisirCategorie"  >
         <s:param name = "request_locale" >es</s:param>
      </s:url>
      
      <s:url var = "saisirCategorieFR" action="saisirCategorie"  >
         <s:param name = "request_locale" >fr</s:param>
      </s:url>

      <s:a href="%{saisirCategorieEN}" >English</s:a>
      <s:a href="%{saisirCategorieFR}" >Spanish</s:a>
      <s:a href="%{saisirCategorieES}" >Français</s:a>
      <div>
      	<s:actionerror/>
      </div>
	<div id="formulaire">
			<s:form method ="post" action="enregistrerCategorie" 
			enctype="multipart/form-data" validate="true"
			theme="bootstrap" cssClass="form-horizontal"
                    label="A sample horizontal Form">
<%--			<s:textfield name="id" id="id"--%>
<%--				key="categorie.form.identifiant" labelposition="left">--%>
<%--			</s:textfield>--%>
			<s:textfield name="nom" id="nom"
				key="categorie.form.nom" labelposition="left">
			</s:textfield>
			<s:textfield name="description" id="description"
				key="categorie.form.description" labelposition="left">
			</s:textfield>
			

<%--			<sx:datetimepicker name="dateInscription" --%>
<%--			key="annuaire.form.dateInscription" --%>
<%--			displayFormat="dd-MM-yyyy"/>  --%>
<%--			<s:file name="photo" key="annuaire.form.photo" />--%>
			<s:submit key="categorie.form.envoyer"></s:submit>
		</s:form>
	</div>
</center>
</body>
</html>
