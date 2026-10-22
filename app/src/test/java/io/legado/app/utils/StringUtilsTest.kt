package io.legado.app.utils

import org.junit.Assert.assertEquals
import org.junit.Test

class StringUtilsTest {

    @Test
    fun formatCategoryTitle_plainTitleUnchanged() {
        assertEquals("玄幻奇幻", StringUtils.formatCategoryTitle("玄幻奇幻"))
        assertEquals("都市", StringUtils.formatCategoryTitle("都市"))
        assertEquals("探索", StringUtils.formatCategoryTitle("探索"))
    }

    @Test
    fun formatCategoryTitle_stripsLeadingAndTrailingJunk() {
        assertEquals("热门小说", StringUtils.formatCategoryTitle("【热门小说】"))
        assertEquals("精选推荐", StringUtils.formatCategoryTitle("  ★精选推荐★  "))
        assertEquals("玄幻奇幻", StringUtils.formatCategoryTitle("―― 玄幻奇幻 ――"))
        assertEquals("甜甜的恋爱", StringUtils.formatCategoryTitle("❤ 甜甜的恋爱 ❤"))
    }

    @Test
    fun formatCategoryTitle_collapsesInnerSymbolRuns() {
        assertEquals("男生频道·都市言情", StringUtils.formatCategoryTitle("男生频道>>都市言情"))
        assertEquals("全本小说·完本", StringUtils.formatCategoryTitle("全本小说::完本"))
        assertEquals("都市·言情·玄幻", StringUtils.formatCategoryTitle("都市|言情|玄幻"))
        assertEquals("都市·Top100", StringUtils.formatCategoryTitle("都市:Top100"))
        assertEquals("玄幻·奇幻", StringUtils.formatCategoryTitle("玄幻-奇幻"))
    }

    @Test
    fun formatCategoryTitle_dropsSpacesBetweenChinese() {
        assertEquals("玄幻奇幻", StringUtils.formatCategoryTitle("玄幻 奇幻"))
        assertEquals("男生频道都市", StringUtils.formatCategoryTitle("男生频道  都市"))
        assertEquals("TOP 10 精选", StringUtils.formatCategoryTitle("TOP 10 精选"))
    }

    @Test
    fun formatCategoryTitle_keepsWesternWordConnectors() {
        assertEquals("Science Fiction", StringUtils.formatCategoryTitle("Science  Fiction"))
        assertEquals("Genre Fantasy", StringUtils.formatCategoryTitle("Genre: Fantasy"))
        assertEquals("Sci-Fi", StringUtils.formatCategoryTitle("Sci-Fi"))
        assertEquals("Sci-Fi", StringUtils.formatCategoryTitle("Sci - Fi"))
        assertEquals("Women's Fiction", StringUtils.formatCategoryTitle("Women's Fiction"))
        assertEquals("AT&T", StringUtils.formatCategoryTitle("AT&T"))
        assertEquals("24/7", StringUtils.formatCategoryTitle("24/7"))
    }

    @Test
    fun formatCategoryTitle_blankOrSymbolOnly() {
        assertEquals("", StringUtils.formatCategoryTitle(null))
        assertEquals("", StringUtils.formatCategoryTitle(""))
        assertEquals("", StringUtils.formatCategoryTitle("   "))
        assertEquals("★✨", StringUtils.formatCategoryTitle("★✨"))
    }
}
