<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : products.xsl
    Created on : March 8, 2020, 12:49 PM
    Author     : huynh
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>products.xsl</title>
                <style>
                    td {
                        padding-left: 10px;
                        padding-right: 10px;
                        padding-top: 5px;
                        padding-bottom: 5px;
                        text-align: center;
                    }
                </style>
            </head>
            <body>
                <xsl:apply-templates/>
            </body>
        </html>
    </xsl:template>
    <xsl:template match="products">
        <table style="margin: auto" border="4" width="75%">
            <xsl:for-each select="items">
                <tr>
                    <td>
                        <xsl:value-of select="code"/>
                    </td>
                    <td>
                        <xsl:value-of select="name"/>
                    </td>
                    <td>
                        <xsl:value-of select="price"/>
                    </td>
                    <td>
                        <xsl:value-of select="description"/>
                    </td>
                </tr>
            </xsl:for-each>
        </table>
    </xsl:template>
</xsl:stylesheet>
