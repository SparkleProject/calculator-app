/* Copyright Vector Technology Services LTD - All Rights Reserved
 *
 * Unauthorized copying of this file or derived binaries, via any medium, is strictly prohibited unless express written
 * consent is given
 *
 * All content is deemed proprietary and confidential
 *
 * Contact Vector LTD legal at "legalservices@vector.co.nz" for more information or to report a breach
 */
package com.ourpretended.calculator.config;

public class ApplicationConstants {

    @SuppressWarnings("InstantiationOfUtilityClass")
    static final ApplicationConstants INSTANCE = new ApplicationConstants();

    public static final String OPERAND_REGEX = "[0-9]+[.]?[0-9]*";
    public static final String OPERATION_REGEX = "[+\\-*/]";
    public static final int RESULT_SCALE_QUOTIENT = 18;


    private ApplicationConstants() {
        // No operation, Empty constructor
    }
}
