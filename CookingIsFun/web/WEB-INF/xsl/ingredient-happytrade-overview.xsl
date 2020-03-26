<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    
    <xsl:output method="xml" encoding="UTF-8"/>

    <xsl:template match="/">
        <ingredients xmlns="http://www.cookingisfun.com/XMLSchema/dev">
            
            <xsl:for-each select="//div[@class='col-xs-6 col-sm-6 col-md-3']/div[@class='product-items']">
                <xsl:variable name="image" select="./div[@class='img-top']"/>
                <xsl:variable name="body" select="./div[@class='info-body']"/>
                <ingredient>
                    <xsl:attribute name="id">
                        <xsl:value-of select="$body/div[@class='buy-fast']/a/@data-id"/>
                    </xsl:attribute>
                    <link>
                        <xsl:value-of select="$body/h3/a/@href"/>
                    </link>
                    <image>
                        <xsl:value-of select="$image/a/img/@src"/>
                    </image>
                </ingredient>
            </xsl:for-each>
            <nextPage>
                <xsl:value-of select="//div[@id='pagination']/a[last()]/@href"/>
            </nextPage>
            
        </ingredients>
    </xsl:template>

</xsl:stylesheet>
