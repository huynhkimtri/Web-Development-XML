<?xml version="1.0" encoding="UTF-8"?>

<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" version="1.0">
    <xsl:output method="xml" encoding="UTF-8" indent="yes"/>

    <xsl:template match="/">
        <recipe xmlns="http://www.cookingisfun.com/XMLSchema/dev">
          
            <name>
                <xsl:value-of select="//div[@class='box-video_info']/h1"/>
            </name>
            
            <description>
                <xsl:value-of select="//div[@class='info-intro']"/>                
            </description>
            
            <image>
                <xsl:value-of select="//div[@class='video-detail_thumb']//img[@class='img-responsive']/@src"/>
            </image>
            
            <servings>
                <xsl:variable name="servings" select="//div[@class='entry-detail_meta mt30']//p[2]"/>
                <!--<xsl:value-of select="translate($servings, '0123456789./-', '')"/>-->
                <xsl:value-of select="translate($servings, translate($servings, '0123456789-', ''), '')"/>
                <!--<xsl:value-of select="//div[@class='entry-detail_meta mt30']//p[2]"/>-->
            </servings>
            
            <prepTime>
                <xsl:variable name="prepTime" select="//div[@class='entry-detail_meta mt30']//p[3]"/>
                <xsl:value-of select="translate($prepTime, translate($prepTime, '0123456789', ''), '')"/>
            </prepTime>
            
            <cookTime>
                <xsl:variable name="cookTime" select="//div[@class='entry-detail_meta mt30']//p[4]"/>
                <xsl:value-of select="translate($cookTime, translate($cookTime, '0123456789', ''), '')"/>
            </cookTime>
            
            <listIngredients>
                <xsl:for-each select="//ul[@class='menu-ingredients']/li">
                    <ingredient>
                        <xsl:variable name="item" select="."/>
                        <xsl:variable name="name" select=".//a"/>
                        <xsl:variable name="tmp" select="substring-before($item, $name)"/>
                        <xsl:variable name="quantity" select="translate($tmp, translate($tmp, '0123456789/-', ''), '')"/>
                        <xsl:variable name="unit" select="normalize-space(substring-after($tmp, $quantity))" />
                        <name>
                            <xsl:value-of select="$name"/>                            
                        </name>
                        <unit>
                            <xsl:value-of select="$unit"/>
                        </unit>
                        <quantity>
                            <xsl:value-of select="$quantity"/>
                        </quantity>
                    </ingredient>
                </xsl:for-each>
            </listIngredients>
            
            <listIntructions>
                <xsl:for-each select="//ul[@class='menu-directions']/li">
                    <instruction>
                        <step>
                            <xsl:value-of select=".//div/span[@class='num-step']"/>
                        </step>
                        <detail>
                            <xsl:value-of select=".//div[@class='it-intro']"/>
                        </detail>
                    </instruction>
                </xsl:for-each>
            </listIntructions>
            
        </recipe>
    </xsl:template>

</xsl:stylesheet>
