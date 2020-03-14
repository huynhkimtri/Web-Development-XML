<?xml version="1.0" encoding="UTF-8"?>

<!--
    Document   : templateHTML.xsl
    Created on : March 8, 2020, 4:34 PM
    Author     : huynh
    Description:
        Purpose of transformation follows.
-->

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="html"/>
    <xsl:template match="/">
        <html>
            <head>
                <title>templateHTML.xsl</title>
            </head>
            <body>
                <xsl:apply-templates />
            </body>
        </html>
    </xsl:template>
    <xsl:template match="Matches">
        <table border="2" style="background-color: cyan">
            <tr>
                <td>Last Name</td>
                <td>Full Name</td>
            </tr>
            <xsl:for-each select="New">
                <tr>
                    <td>
                        <xsl:value-of select="Match"/>
                    </td>
                    <td>
                        <xsl:value-of select="Name"/>
                    </td>
                </tr>
            </xsl:for-each>
        </table>
    </xsl:template>
</xsl:stylesheet>
