package com.starter.template.util;

import android.view.View;

import com.starter.template.R;
import com.starter.template.network.base.CustomThrowable;
import com.starter.template.ui.base.BaseContract;
import com.starter.template.util.common.UiUtils;
import com.starter.template.util.constants.AppConstants;

public class AppUIUtils {

    public static void showNetworkUnAvailableAlert(View content, View.OnClickListener listener) {
        UiUtils.longSnackBarWithTag(content, R.string.internet_unavailable, R.string.retry, listener);
    }

    public static void onGenericNetworkFail(Throwable cause, BaseContract.BaseView view) {
        view.dismissNetworkProgress();
        if (cause != null) {
            cause.printStackTrace();
        }
        if (cause instanceof CustomThrowable) {
//            if (((CustomThrowable) cause).isAuthError()) {
//                AppUIUtils.triggerAutoLogout(view);
//            } else {
            view.showSnack(cause.getMessage());
//            }
        } else {
            view.showSnack(AppConstants.GENERIC_NETWORK_ERROR_MSG);
        }
    }

//    private static void triggerAutoLogout(final BaseContract.BaseView view) {
//        final Context context = view.getViewContext();
//        AppPreferences pref = AppPreferences.getPref(context);
//        final String userName = pref.getUserName();
//        final String password = pref.getPassword();
//        pref.clearUser();
//
//        new MaterialDialog.Builder(context)
//                .title(R.string.auth_failure_title)
//                .content(R.string.auth_failure_content)
//                .cancelable(false)
//                .positiveText(R.string.ok)
//                .onPositive(new MaterialDialog.SingleButtonCallback() {
//                    @Override
//                    public void onClick(
//                            @NonNull MaterialDialog dialog,
//                            @NonNull DialogAction which) {
//                        clearTaskAndOpenLogin(context, userName, password);
//                    }
//                })
//                .show();
//    }
//
//    private static void clearTaskAndOpenLogin(Context context, String userName, String password) {
//        Intent intent = new Intent(context, LoginActivity.class);
//        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        intent.putExtra(BundleTags.EXTRA_USERNAME, userName);
//        intent.putExtra(BundleTags.EXTRA_PASSWORD, password);
//        Bundle options = CustomAnimationUtils.getSlideInFromBottomStartBundle(context);
//        context.startActivity(intent, options);
//    }

//    /**
//     * Make  the string as URL with their position using spannable string builder and convert
//     * spannable string into html content for access text view as link.
//     *
//     * @return spanned html text.
//     */
//    public static String getCopyrightsString(Context context) {
//        SpannableStringBuilder copyrightStringBuilder =
//                new SpannableStringBuilder(context.getResources().getString(R.string.about_copyrights));
//        copyrightStringBuilder.setSpan(new URLSpan(context.getString(R.string.company_url)), 2, 24, 0);
//        return Html.toHtml(copyrightStringBuilder);
//
//    }
}
