package com.example.internlink;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.card.MaterialCardView;
import com.google.android.material.search.SearchBar;

public class CompanyHelpCenterActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private SearchBar searchBar;
    private MaterialCardView postProjectCard, manageApplicantsCard,
            videoTutorialsCard, knowledgeBaseCard;
    private Button supportCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_center_company);

        initializeToolbar();
        initializeViews();
        setupClickListeners();
    }

    private void initializeToolbar() {
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Enterprise Support Hub");
            getSupportActionBar().setSubtitle("Premium Assistance Center");
        }
        toolbar.setNavigationOnClickListener(v -> supportFinishAfterTransition());
    }

    @SuppressLint("WrongViewCast")
    private void initializeViews() {
        searchBar = findViewById(R.id.searchBar);
        postProjectCard = findViewById(R.id.postProjectCard);
        manageApplicantsCard = findViewById(R.id.manageApplicantsCard);
        videoTutorialsCard = findViewById(R.id.videoTutorialsCard);
        knowledgeBaseCard = findViewById(R.id.knowledgeBaseCard);
        supportCard = findViewById(R.id.contactSupportButton);
    }

    private void setupClickListeners() {
        searchBar.setOnClickListener(v ->
                Toast.makeText(this, "Search our knowledge base for instant answers", Toast.LENGTH_SHORT).show());

        postProjectCard.setOnClickListener(v -> showTutorialPopup(
                R.drawable.ic_post_project,
                "\uD83D\uDCE4 Post a Project",
                "Learn how to post a new project and set quiz options.",
                new String[]{
                        "1. Tap 'Create Project' from dashboard",
                        "2. Fill out details and attach files",
                        "3. Enable optional quiz for applicant screening",
                        "4. Set deadlines and milestones",
                        "5. Click 'Post' to publish your project"
                },
                new String[]{"Quiz Option Available", "Top Projects Get 2x More Applications"},
                "PRO TIP: Use clear project titles to attract talent!",
                "POST A PROJECT",
                R.color.colorPostProject
        ));

        manageApplicantsCard.setOnClickListener(v -> showTutorialPopup(
                R.drawable.ic_applicant_management,
                "Manage Applicants",
                "Tips for reviewing applications and interacting with applicants.",
                new String[]{
                        "• View all applicants under 'Applications' tab",
                        "• Filter by quiz score or tags",
                        "• Shortlist or reject with one tap",
                        "• Use chat or email for communication",
                        "• Schedule interviews via calendar integration"
                },
                new String[]{"Smart Filters Included", "Chat-Enabled Profiles"},
                "PRO TIP: Use feedback notes to collaborate with your team.",
                "REVIEW APPLICANTS",
                R.color.colorManageCandidates
        ));

        videoTutorialsCard.setOnClickListener(v -> showTutorialPopup(
                R.drawable.ic_video_tutorial,
                "\uD83C\uDFA5 Video Tutorials",
                "Watch quick videos explaining key features.",
                new String[]{
                        "▶ How to Post a Project (3:20)",
                        "▶ Screening Applicants with Quizzes (4:05)",
                        "▶ Reviewing Applications (2:50)",
                        "▶ Editing and Archiving Projects (3:15)"
                },
                new String[]{"Firebase Hosted", "Optimized for Mobile"},
                "PRO TIP: Save favorites to your dashboard for quick access.",
                "WATCH VIDEOS",
                R.color.colorVideoTutorials
        ));

        knowledgeBaseCard.setOnClickListener(v -> showTutorialPopup(
                R.drawable.ic_knowledge_base,
                "\uD83D\uDCDA Knowledge Base",
                "Browse frequently asked questions and guides.",
                new String[]{
                        "• Account Setup",
                        "• Posting & Editing Projects",
                        "• Reviewing Applicants",
                        "• Troubleshooting & Support",
                        "• Legal and Compliance Tips"
                },
                new String[]{"200+ Articles", "Smart Search Available"},
                "PRO TIP: Use keywords like 'quiz' or 'edit' for faster results.",
                "BROWSE FAQS",
                R.color.colorKnowledgeBase
        ));

        supportCard.setOnClickListener(v -> showTutorialPopup(
                R.drawable.ic_help,
                "\u2709\uFE0F Contact Support",
                "Get in touch with our support team.",
                new String[]{
                        "• Submit a ticket: Enter name, email, issue, message",
                        "• Email: support@InternLink.com",
                        "• Call: +961 1 555 000 (9am–6pm)",
                        "• Chat: Click on chat bubble in corner",
                        "• Response time: Under 24 hours"
                },
                new String[]{"Live Chat Enabled", "Dedicated Support Team"},
                "PRO TIP: Include screenshots to help us assist you faster.",
                "GET SUPPORT",
                R.color.colorSupport
        ));
    }

    private void showTutorialPopup(int iconRes, String title, String description,
                                   String[] steps, String[] stats, String proTip,
                                   String actionText, int colorRes) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this, R.style.ProfessionalDialogTheme);
        View view = LayoutInflater.from(this).inflate(R.layout.popup_tutorial, null);

        ImageView icon = view.findViewById(R.id.popup_icon);
        TextView titleView = view.findViewById(R.id.popup_title);
        TextView descriptionView = view.findViewById(R.id.popup_description);
        LinearLayout stepsLayout = view.findViewById(R.id.popup_steps);
        TextView stat1 = view.findViewById(R.id.popup_stat1);
        TextView stat2 = view.findViewById(R.id.popup_stat2);
        TextView proTipView = view.findViewById(R.id.popup_pro_tip);
        Button actionButton = view.findViewById(R.id.popup_action_button);
        View accentBar = view.findViewById(R.id.accent_bar);

        icon.setImageResource(iconRes);
        titleView.setText(title);
        descriptionView.setText(description);
        accentBar.setBackgroundColor(ContextCompat.getColor(this, colorRes));
        actionButton.setBackgroundColor(ContextCompat.getColor(this, colorRes));

        stepsLayout.removeAllViews();
        for (String step : steps) {
            TextView stepView = (TextView) LayoutInflater.from(this)
                    .inflate(R.layout.item_step, stepsLayout, false);
            stepView.setText(step);
            stepsLayout.addView(stepView);
        }

        stat1.setText(stats[0]);
        stat2.setText(stats[1]);
        proTipView.setText(proTip);
        actionButton.setText(actionText);

        AlertDialog dialog = builder.setView(view).create();
        if (dialog.getWindow() != null) {
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.getWindow().setDimAmount(0.6f);
        }

        actionButton.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.help_menu, menu);
        MenuItem item = menu.findItem(R.id.action_notifications);
        Drawable icon = item.getIcon();
        if (icon != null) {
            icon.setTint(ContextCompat.getColor(this, android.R.color.black));
        }
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_notifications) {
            startActivity(new Intent(this, CompanyAnnounce.class));
            return true;
        } else if (id == R.id.action_help) {
            showTutorialPopup(
                    R.drawable.ic_help,
                    "ℹ Help Menu",
                    "Use this panel to access guidance and support.",
                    new String[]{
                            "• Post projects with quizzes",
                            "• Manage your applicants efficiently",
                            "• Watch tutorials and read FAQs",
                            "• Contact support anytime"
                    },
                    new String[]{"Guided UI", "24/7 Access"},
                    "TIP: Visit each section from the cards below.",
                    "GOT IT",
                    R.color.blue_500
            );
            return true;
        } else if (id == R.id.action_settings) {
            startActivity(new Intent(this, CompanySettingsActivity.class));
            return true;
        } else if (id == R.id.action_feedback) {
            Toast.makeText(this, "We appreciate your feedback!", Toast.LENGTH_SHORT).show();
            return true;
        } else if (id == R.id.action_about) {
            Toast.makeText(this, "InternLink v1.0 – Empowering Students & Employers", Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressLint("MissingSuperCall")
    @Override
    public void onBackPressed() {
        supportFinishAfterTransition();
    }
}