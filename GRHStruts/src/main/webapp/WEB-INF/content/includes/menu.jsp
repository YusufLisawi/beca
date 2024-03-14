<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<s:set var="activeMenu" scope="page">${param.active}</s:set>

<div class="d-flex flex-column flex-shrink-0 p-3 text-bg-light" style="width: 280px;">
  <ul class="nav nav-pills flex-column mb-auto">
    <li class="nav-item">
      <s:url var="index_url" action="index"/>
      <s:if test='#attr.activeMenu == "index"'>
        <s:a href="%{index_url}" cssClass="nav-link active"><i class="bi bi-grid"></i> Horizontal Form Layout</s:a>
      </s:if>
      <s:else>
        <s:a href="%{index_url}" cssClass="nav-link"><i class="bi bi-grid"></i> Horizontal Form Layout</s:a>
      </s:else>
    </li>
    <li class="nav-item">
      <s:url var="administrateur_url" action="administrateur"/>
      <s:if test='#attr.activeMenu == "administrateur"'>
        <s:a href="%{administrateur_url}" cssClass="nav-link active"><i class="bi bi-calculator"></i> Gestion Administrateurs</s:a>
      </s:if>
      <s:else>
        <s:a href="%{administrateur_url}" cssClass="nav-link"><i class="bi bi-calculator-fill"></i> Gestion Administrateurs</s:a>
      </s:else>
    </li>
    <li class="nav-item">
      <s:url var="developpeur_url" action="developpeur"/>
      <s:if test='#attr.activeMenu == "developpeur"'>
        <s:a href="%{developpeur_url}" cssClass="nav-link active"><i class="bi bi-pc-display-horizontal"></i> Gestion des Developpeurs</s:a>
      </s:if>
      <s:else>
        <s:a href="%{developpeur_url}" cssClass="nav-link"><i class="bi bi-pc-display"></i> Gestion des Developpeurs</s:a>
      </s:else>
    </li>
    <li class="nav-item">
      <s:url var="projet_url" action="projet"/>
      <s:if test='#attr.activeMenu == "projet"'>
        <s:a href="%{projet_url}" cssClass="nav-link active"><i class="bi bi-diagram-3"></i> Gestion des Projets</s:a>
      </s:if>
      <s:else>
        <s:a href="%{projet_url}" cssClass="nav-link"><i class="bi bi-diagram-3-fill"></i> Gestion des Projets</s:a>
      </s:else>
    </li>
    <li class="nav-item">
      <s:url var="validation_url" action="validation"/>
      <s:if test='#attr.activeMenu == "validation"'>
        <s:a href="%{validation_url}" cssClass="nav-link active"><i class="bi bi-exclamation-diamond-fill"></i> Client Side Validation</s:a>
      </s:if>
      <s:else>
        <s:a href="%{validation_url}" cssClass="nav-link"><i class="bi bi-exclamation-diamond-fill"></i> Client Side Validation</s:a>
      </s:else>
    </li>
    <li class="nav-item">
      <s:url var="advanced_url" action="advanced"/>
      <s:if test='#attr.activeMenu == "advanced"'>
        <s:a href="%{advanced_url}" cssClass="nav-link active"><i class="bi bi-easel2-fill"></i> Advanced Examples</s:a>
      </s:if>
      <s:else>
        <s:a href="%{advanced_url}" cssClass="nav-link"><i class="bi bi-easel2-fill"></i> Advanced Examples</s:a>
      </s:else>
    </li>
    <li class="nav-item">
      <s:url var="jquery_url" action="jquery"/>
      <s:if test='#attr.activeMenu == "jquery"'>
        <s:a href="%{jquery_url}" cssClass="nav-link active"><i class="bi bi-capsule-pill"></i> Struts2 jQuery UI Examples</s:a>
      </s:if>
      <s:else>
        <s:a href="%{jquery_url}" cssClass="nav-link"><i class="bi bi-capsule-pill"></i> Struts2 jQuery UI Examples</s:a>
      </s:else>
    </li>
    <li class="nav-item">
      <s:url var="custom_url" action="custom"/>
      <s:if test='#attr.activeMenu == "custom"'>
        <s:a href="%{custom_url}" cssClass="nav-link active"><i class="bi bi-boxes"></i> With Custom Theme</s:a>
      </s:if>
      <s:else>
        <s:a href="%{custom_url}" cssClass="nav-link"><i class="bi bi-boxes"></i> With Custom Theme</s:a>
      </s:else>
    </li>
    <li class="nav-item">
      <s:url var="customlayout_url" action="customlayout"/>
      <s:if test='#attr.activeMenu == "customlayout"'>
        <s:a href="%{customlayout_url}" cssClass="nav-link active"><i class="bi bi-layout-three-columns"></i> Multi Column Forms</s:a>
      </s:if>
      <s:else>
        <s:a href="%{customlayout_url}" cssClass="nav-link"><i class="bi bi-layout-three-columns"></i> Multi Column Forms</s:a>
      </s:else>
    </li>
  </ul>
</div>