<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="sj" uri="/struts-jquery-tags" %>
<%@ taglib prefix="sb" uri="/struts-bootstrap-tags" %>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="keywords" content="struts2, twitter, bootstrap, plugin, showcase"/>
    <meta name="description" content="Enable Client Side Validation - A Showcase for the Struts2 Bootstrap Plugin"/>
    <title>Gestion des projets</title>

    <s:head/>
    <sj:head jqueryui="true"/>
    <sb:head includeScripts="true"/>

</head>
<body>

<s:include value="includes/topMenu.jsp">
    <s:param name="active">home</s:param>
</s:include>


<div class="col-md-3">
    <s:include value="includes/menu.jsp">
        <s:param name="active">projet</s:param>
    </s:include>
</div>
<div class="col-md-9">

    <h1>Gestion des projets</h1>

    <s:actionerror theme="bootstrap"/>
    <s:actionmessage theme="bootstrap"/>
    <s:fielderror theme="bootstrap"/>


    <s:form id="projetForm"
            action="add-projet"
            theme="bootstrap"
            cssClass="form-horizontal"
            labelCssClass="col-sm-2"
            elementCssClass="col-sm-7"
            label="Nouvel projet">

        <s:textfield
                label="Type"
                name="projet.type"
                requiredLabel="true"
                tooltip="Entrez le Type de projet"/>

        <s:textfield
                label="Nom"
                name="projet.nom"
                requiredLabel="true"
                tooltip="Entrez le Nom de projet"/>
        <s:optiontransferselect
                label="Affectation des employes"
                tooltip="Affecter les employes a un projet"
                name="listIds"
                leftTitle="Tous les employes"
                rightTitle="Les employes affecte"
                list="mapPersonnels"
                multiple="true"
                headerKey="headerKey"
                headerValue="--- Please Select ---"
                emptyOption="true"
                doubleList="mapPersonnelsAffected"
                doubleName="listIdsAffected"
                doubleHeaderKey="doubleHeaderKey"
                doubleHeaderValue="--- Please Select ---"
                doubleEmptyOption="true"
                doubleMultiple="true"/>

        <div class="form-group">
            <div class="col-sm-offset-3 col-md-9">
                <sj:submit
                        cssClass="btn btn-primary"
                        value="Ajouter"
                        formIds="projetForm" validate="true"
                        validateFunction="bootstrapValidation"
                />
            </div>
        </div>
    </s:form>

    <div class="my-4">
        <h2 class="py-2">Liste des projets</h2>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Id</th>
                    <th>Type</th>
                    <th>Nom</th>
                    <th>Action</th>
                </tr>
                </thead>
                <tbody>
                <s:iterator value="listProjets">
                    <tr>
                        <td><s:property value="id"/></td>
                        <td><s:property value="type"/></td>
                        <td><s:property value="nom"/></td>
                        <td>
                            <s:form action="/delete-projet">
                                <s:hidden name="id" value="%{id}"/>
                                <s:submit value="Delete" cssClass="btn btn-danger"/>
                            </s:form>
                        </td>
                    </tr>
                </s:iterator>
                </tbody>
            </table>
        </div>
    </div>
</div>


<s:include value="includes/footer.jsp"/>

</body>
</html>
