<?xml version="1.0"?>
<recipe>
    <#include "../common/recipe_manifest.xml.ftl" />

    <instantiate from="root/src/app_package/simple.xml.ftl"
                 to="${escapeXmlAttribute(resOut)}/layout/${simpleLayoutName}.xml" />

    <instantiate from="root/src/app_package/SimpleActivity.kotlin.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${activityClass}Activity.kt" />

    <instantiate from="root/src/app_package/SimpleView.kotlin.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${activityClass}View.kt" />

    <instantiate from="root/src/app_package/SimplePresenter.kotlin.ftl"
                   to="${escapeXmlAttribute(srcOut)}/${activityClass}Presenter.kt" />

    <open file="${escapeXmlAttribute(srcOut)}/${activityClass}Activity.java" />
</recipe>
