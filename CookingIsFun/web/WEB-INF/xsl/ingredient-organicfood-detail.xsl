<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">

    <xsl:output method="xml" encoding="UTF-8"/>

    <xsl:template match="/">
        <ingredient xmlns="http://www.cookingisfun.com/XMLSchema/dev">

            <xsl:variable name="title" select="//div[@class='product-title']//h1"/>
            <xsl:variable name="description" select="//div[@id='mota']/p[1]/span"/>

            <id>
                <xsl:value-of select="//div[@class='select clearfix']/select[@id='product-select']/option/@value"/>
            </id>
            <name>
                <xsl:value-of select="$title"/>
            </name>
            <price>
                <xsl:variable name="price" select="//div[@class='product-price clearfix']//span"/>
                <xsl:value-of select="translate($price, ',₫', '')"/>
            </price>
            <description>
                <xsl:value-of select="$description"/>
            </description>

        </ingredient>
    </xsl:template>

</xsl:stylesheet>
