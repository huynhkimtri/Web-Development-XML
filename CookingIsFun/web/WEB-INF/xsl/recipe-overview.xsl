<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" encoding="UTF-8"/>

    <xsl:template match="/">
        <recipes xmlns="http://www.cookingisfun.com/XMLSchema/dev">
            
            <xsl:for-each select="//div[@class='it-body_bottom col-md-8 col-sm-8']">
                <recipe>
                    <xsl:variable name="href" select=".//h3/a/@href" />
                    <xsl:attribute name="id">
                        <xsl:value-of select="substring-before(substring-after($href, '/cong-thuc/'),'-')"/>
                    </xsl:attribute>
                    <link>
                        <xsl:value-of select="$href"/>
                    </link>
                    <image>
                        <xsl:value-of select=".//preceding-sibling::div[@class='it-img_bottom col-md-4 col-sm-4']/a/image/@src"/>
                    </image>
                </recipe>
            </xsl:for-each>
            
            <nextPage>
                <xsl:value-of select="//a[@class='next']/@href"/>
            </nextPage>
        </recipes>
    </xsl:template>

</xsl:stylesheet>
