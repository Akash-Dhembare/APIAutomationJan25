package com.akash.dhembare2000.ex_33022025.Verification;

import io.qameta.allure.*;
import org.testng.annotations.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.*;

public class Verification002 {
    @Description("This test attempts to log into the website using a login and a password")
    @Severity(SeverityLevel.CRITICAL)
    @Owner("Akash Dhembare")
    @Link(name="Website", url = "https://google.com/")
    @Issue("AUTH-123")
    @TmsLink("TMS-456")
    @Test
    public void test_verify_assertj(){
        String name="Akash";
        assertThat(name).isEqualTo("Akash").isNotNull().isNotEmpty();

        List<String> names= Arrays.asList("Akash", "Vikas", "Shobha");
        assertThat(names).hasSize(3).isNotNull();

        LocalDate date= LocalDate.now();
        System.out.println(date);

        assertThat(date).isAfterOrEqualTo(LocalDate.of(2021, 1,1))
                .isBeforeOrEqualTo(LocalDate.of(2025,12,12))
                .isBetween((LocalDate.of(2024,12,12)),
                        LocalDate.of(2025, 12, 15));

        File file = new File("C:\\Users\\teju3\\IdeaProjects\\APIAutomationJan25\\src\\test\\java\\com\\akash\\dhembare2000\\ex_33022025\\Verification\\Testdata.json");
        assertThat(file).canRead().isFile().exists();

        Map<String, Integer> ages=new HashMap<>();
        ages.put("Akash", 25);
        ages.put("Vikas", 50);
        ages.put("Shobha", 45);

        assertThat(ages).containsEntry("Akash",25).hasSize(3).doesNotContainValue(35);

    }
}
