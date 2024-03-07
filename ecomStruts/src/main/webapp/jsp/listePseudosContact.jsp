<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ taglib prefix="sx" uri="/struts-dojo-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<s:head />
<sx:head />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title><s:text name="annuaire.form.newContact.title" /></title>
<script>
	function show_details() {
		dojo.event.topic.publish("show_detail");
	}
</script>
</head>
<body>
	<s:form>
			<sx:autocompleter label="Choisir un Contact" list="listPseudos"
				id="lstPseudo" name="pseudo" />
	</s:form>
	<div>
		<h1>Liste des Contacts</h1>
		 <s:form id="listPseudo_form" name="lst" theme="simple">
                    <table border="0">
                        <tr>
                            <td>
                                <s:updownselect list="listPseudos" name="pseudo" size="10"
                                                label="Liste des contacts" allowMoveDown="false" allowMoveUp="false"
                                                allowSelectAll="false" multiple="false"
                                                onchange="javascript:show_details();return false;">
                                </s:updownselect>
                            </td>
                            <td><s:url var="details_url" action="detailsContact" /></td>
                            <td>
                                <sx:div href="%{details_url}" listenTopics="show_detail"
                                        theme="ajax" formId="listPseudo_form"></sx:div>
                            </td>
                        </tr>
                    </table>
                </s:form>
	<div>

</body>
</html>
