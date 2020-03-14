<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : outputPersons.xsl
    Created on : March 8, 2020, 4:27 PM
    Author     : huynh
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" indent="yes"/>
    <xsl:template match="/">
        <Matches>
            <xsl:apply-templates select="*"/>
        </Matches>
    </xsl:template>
    <xsl:template match="name">
        <New>
            <Match  style="color: red; font-weight: bold">
                <xsl:value-of select="lastname"/>
            </Match>
            <Name  style="color: blue; font-size: x-large; font-weight: bold">
                <xsl:value-of select="."/>
            </Name>
        </New>
    </xsl:template>
</xsl:stylesheet>
