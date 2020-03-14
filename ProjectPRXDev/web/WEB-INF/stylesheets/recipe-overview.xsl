<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    
    <xsl:output method="xml" encoding="UTF-8"/>

    <xsl:template match="/">
        <recipes xmlns="http://www.project-xml.com/XMLSchema/dev">

            <xsl:for-each select="//div[@class='it-new_bottom clearfix ']/div[@class='row']">
                
                <xsl:variable name="body" select="./div[@class='it-body_bottom col-md-8 col-sm-8']"/>
                <xsl:variable name="image" select="./div[@class='it-img_bottom col-md-4 col-sm-4']"/>
                
                <recipe>
                    <xsl:variable name="link" select="$body/h3/a/@href" />
                    <id>
                        <xsl:value-of select="substring-before(substring-after($link, '/cong-thuc/'),'-')"/>
                    </id>
                    <link>
                        <xsl:value-of select="$link"/>
                    </link>
                    <image>
                        <xsl:value-of select="$image/a/image/@src"/>
                    </image>
                </recipe>
                
            </xsl:for-each>
            
            <nextpage>
                <xsl:value-of select="//a[@class='next']/@href"/>
            </nextpage>
            
        </recipes>
    </xsl:template>

</xsl:stylesheet>
