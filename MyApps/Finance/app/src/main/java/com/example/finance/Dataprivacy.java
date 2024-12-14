package com.example.finance;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Dataprivacy extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        setContentView(R.layout.activity_dataprivacy);
        getWindow().setStatusBarColor(ContextCompat.getColor(Dataprivacy.this, R.color.navbar));

        TextView t = findViewById(R.id.DataPrivacyInfo);
        String datap = "Privacy Policy\n" +
                "\n" +
                "At Finance, we are committed to protecting and respecting your privacy. This Privacy Policy outlines how we collect, use, disclose, and safeguard your personal information when you use our mobile application (\"App\"). By accessing and using this App, you agree to the terms of this Privacy Policy.\n" +
                "1. Information We Collect\n" +
                "\n" +
                "We may collect the following types of information when you use our App:\n" +
                "a. Personal Information\n" +
                "\n" +
                "When you sign up for or use our App, we may ask for personal information, including but not limited to:\n" +
                "\n" +
                "    Name\n" +
                "    Email address\n" +
                "    Phone number\n" +
                "    Payment details (if applicable)\n" +
                "    Profile information (e.g., username, avatar)\n" +
                "\n" +
                "b. Usage Data\n" +
                "\n" +
                "We may collect non-personal information related to your usage of the App, such as:\n" +
                "\n" +
                "    Device information (e.g., device model, operating system version)\n" +
                "    App usage data (e.g., frequency of use, time spent in the app)\n" +
                "    Log files (e.g., IP address, browser type)\n" +
                "\n" +
                "c. Location Data\n" +
                "\n" +
                "With your permission, we may access your location to provide location-based services. This information will only be used for the purposes outlined in this policy and can be disabled at any time through your device settings.\n" +
                "d. Cookies and Tracking Technologies\n" +
                "\n" +
                "We use cookies and similar tracking technologies to improve your experience on our App. Cookies help us analyze trends, track user movements, and enhance functionality. You can control cookie settings in your device settings.\n" +
                "2. How We Use Your Information\n" +
                "\n" +
                "We use the information we collect to:\n" +
                "\n" +
                "    Provide and personalize our services to you\n" +
                "    Improve the functionality and performance of the App\n" +
                "    Process payments and fulfill transactions\n" +
                "    Respond to customer support requests\n" +
                "    Send you relevant information about updates, promotions, or offers (if you opt in)\n" +
                "    Analyze usage patterns to improve user experience\n" +
                "    Detect and prevent fraud or unauthorized activity\n" +
                "\n" +
                "3. How We Share Your Information\n" +
                "\n" +
                "We do not sell, rent, or trade your personal information to third parties. However, we may share your data with trusted third-party service providers who assist in operating the App, such as:\n" +
                "\n" +
                "    Cloud storage providers\n" +
                "    Payment processors\n" +
                "    Customer support tools\n" +
                "    Data analytics providers\n" +
                "\n" +
                "These third-party partners are required to handle your personal data securely and only for purposes necessary to fulfill their functions.\n" +
                "\n" +
                "In certain situations, we may disclose your information if required to do so by law, such as in response to legal processes, government requests, or in the event of a security breach.\n" +
                "4. Data Security\n" +
                "\n" +
                "We take the security of your personal information seriously. We implement a variety of security measures, including encryption, access control, and regular security audits, to protect your data from unauthorized access, alteration, or disclosure.\n" +
                "\n" +
                "However, no method of transmission over the internet or electronic storage is completely secure. While we strive to use commercially acceptable means to protect your information, we cannot guarantee absolute security.\n" +
                "5. Your Rights and Choices\n" +
                "\n" +
                "As a user, you have certain rights regarding your personal information:\n" +
                "\n" +
                "    Access and Correction: You can request access to the personal data we have collected and ask for corrections if the data is inaccurate.\n" +
                "    Deletion: You may request that we delete your personal information, subject to certain legal limitations.\n" +
                "    Opt-out: You can choose not to receive promotional communications by following the unsubscribe instructions in our emails or contacting us directly.\n" +
                "    Data Portability: You may request a copy of your data in a portable format for transfer to another service.\n" +
                "\n" +
                "If you would like to exercise any of your rights, please contact us using the information below.\n" +
                "6. Children's Privacy\n" +
                "\n" +
                "Our App is not intended for use by children under the age of 13. We do not knowingly collect or solicit personal information from children under 13. If we learn that we have inadvertently collected personal information from a child under the age of 13, we will take steps to delete that information as quickly as possible.\n" +
                "7. Changes to This Privacy Policy\n" +
                "\n" +
                "We reserve the right to update or modify this Privacy Policy at any time. Any changes will be posted on this page with an updated revision date. Please review this policy periodically to stay informed about how we are protecting your data.\n" +
                "\n" +
                "If we make significant changes to the policy, we will notify you via the App or through other communication channels.\n" +
                "8. Contact Us\n" +
                "\n" +
                "If you have any questions or concerns about this Privacy Policy or how we handle your personal information, please contact us at:\n" +
                "\n" +
                "[Your Company Name]\n" +
                "[Company Address]\n" +
                "Email: [support@yourapp.com]\n" +
                "Phone: [123-456-7890]";
        t.setText(datap);

        ImageView back = findViewById(R.id.accback2);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
    }
}