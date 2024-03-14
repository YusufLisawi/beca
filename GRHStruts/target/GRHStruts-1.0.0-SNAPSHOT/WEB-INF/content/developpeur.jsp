<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords" content="struts2, twitter, bootstrap, plugin, showcase" />
    <meta name="description" content="Enable Client Side Validation - A Showcase for the Struts2 Bootstrap Plugin" />
    <title>Gestion des Developpeurs</title>

    <s:head />
    <sj:head jqueryui="true"/>
    <sb:head includeScripts="true"/>

</head>
<body>

<s:include value="includes/topMenu.jsp">
    <s:param name="active">home</s:param>
</s:include>


<div class="col-md-3">
    <s:include value="includes/menu.jsp">
        <s:param name="active">developpeur</s:param>
    </s:include>
</div>
<div class="col-md-9">

    <h1>Gestion des Developpeurs</h1>

    <s:actionerror theme="bootstrap"/>
    <s:actionmessage theme="bootstrap"/>
    <s:fielderror theme="bootstrap"/>


    <s:form id="devForm"
            action="add-dev"
            theme="bootstrap"
            cssClass="form-horizontal"
            labelCssClass="col-sm-2"
            elementCssClass="col-sm-7"
            enctype="multipart/form-data"
            label="Nouvel Developpeur">

        <s:textfield
                label="Nom"
                name="dev.nom"
                requiredLabel="true"
                tooltip="Entrez le Nom de developpeur"/>

        <s:textfield
                label="Prenom"
                name="dev.prenom"
                requiredLabel="true"
                tooltip="Entrez le Prenom de developpeur"/>

        <s:textfield
                label="Email"
                name="dev.email"
                tooltip="Entrez l'email de developpeur"/>

        <s:textfield
                label="Language"
                name="dev.language"
                requiredLabel="true"
                tooltip="Entrez la language de developpeur"/>

        <sj:datepicker
                name = "dateNaissance"
                parentTheme="bootstrap"
                label="Date de Naissance"
                cssClass="form-control"
                elementCssClass="col-md-8"
                labelCssClass="col-md-4"
                displayFormat="dd/mm/yy"
                showOn="focus"
                inputAppendIcon="calendar"
        />

        <s:file
                tooltip="Ajouter une Photo"
                label="Photo"
                name="photo"/>

        <sj:datepicker
                name = "dateEmbauche"
                parentTheme="bootstrap"
                label="Date d'Embauche"
                cssClass="form-control"
                elementCssClass="col-md-8"
                labelCssClass="col-md-4"
                displayFormat="dd/mm/yy"
                showOn="focus"
                inputAppendIcon="calendar"
        />


        <div class="form-group">
            <div class="col-sm-offset-3 col-md-9">
                <sj:submit
                        cssClass="btn btn-primary"
                        value="Ajouter"
                        formIds="devForm" validate="true"
                        validateFunction="bootstrapValidation" />
            </div>
        </div>

    </s:form>
</div>
<div class="table-responsive">
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Photo</th>
            <th>Nom</th>
            <th>Prenom</th>
            <th>Email</th>
            <th>Language</th>
            <th>Date de Naissance</th>
            <th>Date d'Embauche</th>
            <th>Action</th>
        </tr>
        </thead>
        <tbody>
        <s:iterator value="listPersonnels">
            <tr>
                <td><img src="uploads/<s:property value="photo"/>" height="50" width="50"/></td>
                <td><s:property value="nom" /></td>
                <td><s:property value="prenom" /></td>
                <td><s:property value="email" /></td>
                <td><s:property value="language" /></td>
                <td><s:property value="dateNaissance" /></td>
                <td><s:property value="dateEmbauche" /></td>
                <td>
                    <s:form action="/delete-dev">
                        <s:hidden name="id" value="%{id}" />
                        <s:submit value="Delete" cssClass="btn btn-danger" />
                    </s:form>
                </td>
            </tr>
        </s:iterator>
        </tbody>
    </table>
</div>


<s:include value="includes/footer.jsp" />

</body>
</html>
