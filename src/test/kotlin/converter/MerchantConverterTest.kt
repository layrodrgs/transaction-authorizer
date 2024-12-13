package converter

import br.com.caju.transaction.adapter.converter.MerchantConverter
import br.com.caju.transaction.core.entity.Merchant
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

class MerchantConverterTest {
    @Test
    fun `should convert Merchant to MerchantDomain correctly`() {
        val merchant = Merchant(mcc = "1234", name = "Merchant A")

        // Act
        val merchantDomain = MerchantConverter.toDomain(merchant)

        // Assert
        assertNotNull(merchantDomain)
        assertEquals("1234", merchantDomain.mcc)
        assertEquals("Merchant A", merchantDomain.name)
    }

    @Test
    fun `should return null values when Merchant is null`() {
        val merchantDomain = MerchantConverter.toDomain(null)

        assertNull(merchantDomain.mcc)
        assertNull(merchantDomain.name)
    }

    @Test
    fun `should handle empty Merchant properties`() {
        val merchant = Merchant(mcc = "", name = "")

        val merchantDomain = MerchantConverter.toDomain(merchant)

        assertNotNull(merchantDomain)
        assertEquals("", merchantDomain.mcc)
        assertEquals("", merchantDomain.name)
    }
}