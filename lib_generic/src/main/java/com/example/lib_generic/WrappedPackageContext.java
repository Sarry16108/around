package com.example.lib_generic;

import android.annotation.TargetApi;
import android.content.ComponentName;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.ActivityInfo;
import android.content.pm.ApplicationInfo;
import android.content.pm.ChangedPackages;
import android.content.pm.FeatureInfo;
import android.content.pm.InstrumentationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageInstaller;
import android.content.pm.PackageManager;
import android.content.pm.PermissionGroupInfo;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.content.pm.SharedLibraryInfo;
import android.content.pm.VersionedPackage;
import android.content.res.Resources;
import android.content.res.XmlResourceParser;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.os.UserHandle;

import java.util.List;

public class WrappedPackageContext extends ContextWrapper {

    private Context apkContext, packageNameContext;

    WrappedPackageContext(Context packageContext) {
        super(packageContext);
    }

    WrappedPackageContext(Context apkContext,Context packageNameContext) {
        super(packageNameContext);
        this.apkContext=apkContext;
        this.packageNameContext=packageNameContext;
    }

    @Override
    public String getPackageName() {
        return packageNameContext.getPackageName();
    }

    @Override
    public Context getApplicationContext() {
        return this;
    }

    @Override
    public PackageManager getPackageManager() {
        return new TestPackageManager(apkContext,packageNameContext);
    }

    public static  Context createApplicationContext(Context apkContext,Context packageNameContext) {
        return new WrappedPackageContext(apkContext,packageNameContext);
    }


    class TestPackageManager extends PackageManager{

        private Context apkContext, packageNameContext;
        public  TestPackageManager(Context apkContext,Context packageNameContext){
            this.apkContext=apkContext;
            this.packageNameContext=packageNameContext;
        }

        @Override
        public PackageInfo getPackageInfo(String packageName, int flags) throws NameNotFoundException {
            packageName =  packageNameContext.getPackageName().equals(packageName)
                    ?
                    apkContext.getPackageName()
                    :
                    packageName;
            return apkContext.getPackageManager().getPackageInfo(packageName,flags
            );
        }

        @TargetApi(26)
        @Override
        public PackageInfo getPackageInfo(VersionedPackage versionedPackage, int flags) throws NameNotFoundException {

            return apkContext.getPackageManager().getPackageInfo(
                    packageNameContext.getPackageName().equals(versionedPackage.getPackageName())
                            ?
                            apkContext.getPackageName()
                            :
                            versionedPackage.getPackageName(),flags
            );
        }

        @Override
        public String[] currentToCanonicalPackageNames(String[] names) {
            return apkContext.getPackageManager().currentToCanonicalPackageNames(names);
        }

        @Override
        public String[] canonicalToCurrentPackageNames(String[] names) {
            return apkContext.getPackageManager().canonicalToCurrentPackageNames(names);
        }

        @Override
        public Intent getLaunchIntentForPackage( String packageName) {
            return apkContext.getPackageManager().getLaunchIntentForPackage(packageName);
        }

        @TargetApi(21)
        @Override
        public Intent getLeanbackLaunchIntentForPackage( String packageName) {
            return apkContext.getPackageManager().getLeanbackLaunchIntentForPackage(packageName);
        }

        @Override
        public int[] getPackageGids(String packageName) throws NameNotFoundException {
            packageName =  packageNameContext.getPackageName().equals(packageName)
                    ?
                    apkContext.getPackageName()
                    :
                    packageName;
            return apkContext.getPackageManager().getPackageGids(packageName);
        }

        @TargetApi(24)
        @Override
        public int[] getPackageGids(String packageName, int flags) throws NameNotFoundException {
            packageName =  packageNameContext.getPackageName().equals(packageName)
                    ?
                    apkContext.getPackageName()
                    :
                    packageName;
            return apkContext.getPackageManager().getPackageGids(packageName,flags);
        }
        @TargetApi(24)
        @Override
        public int getPackageUid(String packageName, int flags) throws NameNotFoundException {
            packageName =  packageNameContext.getPackageName().equals(packageName)
                    ?
                    apkContext.getPackageName()
                    :
                    packageName;
            return apkContext.getPackageManager().getPackageUid(packageName,flags);
        }

        @Override
        public PermissionInfo getPermissionInfo(String name, int flags) throws NameNotFoundException {
            return apkContext.getPackageManager().getPermissionInfo(name,flags);
        }

        @Override
        public List<PermissionInfo> queryPermissionsByGroup(String group, int flags) throws NameNotFoundException {
            return apkContext.getPackageManager().queryPermissionsByGroup(group,flags);
        }

        @Override
        public PermissionGroupInfo getPermissionGroupInfo(String name, int flags) throws NameNotFoundException {
            return apkContext.getPackageManager().getPermissionGroupInfo(name,flags);
        }

        @Override
        public List<PermissionGroupInfo> getAllPermissionGroups(int flags) {
            return apkContext.getPackageManager().getAllPermissionGroups(flags);
        }

        @Override
        public ApplicationInfo getApplicationInfo(String packageName, int flags) throws NameNotFoundException {
            packageName =  packageNameContext.getPackageName().equals(packageName)
                    ?
                    apkContext.getPackageName()
                    :
                    packageName;
            return apkContext.getPackageManager().getApplicationInfo(packageName
                    ,flags);
        }

        @Override
        public ActivityInfo getActivityInfo(ComponentName component, int flags) throws NameNotFoundException {
            return apkContext.getPackageManager().getActivityInfo(component,flags);
        }

        @Override
        public ActivityInfo getReceiverInfo(ComponentName component, int flags) throws NameNotFoundException {
            return apkContext.getPackageManager().getReceiverInfo(component,flags);
        }

        @Override
        public ServiceInfo getServiceInfo(ComponentName component, int flags) throws NameNotFoundException {
            return apkContext.getPackageManager().getServiceInfo(component,flags);
        }

        @Override
        public ProviderInfo getProviderInfo(ComponentName component, int flags) throws NameNotFoundException {
            return apkContext.getPackageManager().getProviderInfo(component,flags);
        }

        @Override
        public List<PackageInfo> getInstalledPackages(int flags) {
            return apkContext.getPackageManager().getInstalledPackages(flags);
        }

        @TargetApi(18)
        @Override
        public List<PackageInfo> getPackagesHoldingPermissions(String[] permissions, int flags) {
            return apkContext.getPackageManager().getPackagesHoldingPermissions(permissions,flags);
        }

        @Override
        public int checkPermission(String permName, String pkgName) {
            return apkContext.getPackageManager().checkPermission(permName,pkgName);
        }

        @TargetApi(23)
        @Override
        public boolean isPermissionRevokedByPolicy( String permName,  String pkgName) {
            return apkContext.getPackageManager().isPermissionRevokedByPolicy(permName,pkgName);
        }

        @Override
        public boolean addPermission(PermissionInfo info) {
            return apkContext.getPackageManager().addPermission(info);
        }

        @Override
        public boolean addPermissionAsync(PermissionInfo info) {
            return apkContext.getPackageManager().addPermissionAsync(info);
        }

        @Override
        public void removePermission(String name) {
             apkContext.getPackageManager().removePermission(name);

        }

        @Override
        public int checkSignatures(String pkg1, String pkg2) {
            return apkContext.getPackageManager().checkSignatures(pkg1,pkg2);
        }

        @Override
        public int checkSignatures(int uid1, int uid2) {
            return apkContext.getPackageManager().checkSignatures(uid1,uid2);
        }

        @Override
        public String[] getPackagesForUid(int uid) {
            return apkContext.getPackageManager().getPackagesForUid(uid);
        }

        @Override
        public String getNameForUid(int uid) {
            return apkContext.getPackageManager().getNameForUid(uid);
        }

        @Override
        public List<ApplicationInfo> getInstalledApplications(int flags) {
            return apkContext.getPackageManager().getInstalledApplications(flags);
        }

        @TargetApi(26)
        @Override
        public boolean isInstantApp() {
            return apkContext.getPackageManager().isInstantApp();
        }

        @TargetApi(26)
        @Override
        public boolean isInstantApp(String packageName) {
            return apkContext.getPackageManager().isInstantApp(packageName);
        }
        @TargetApi(26)
        @Override
        public int getInstantAppCookieMaxBytes() {
            return apkContext.getPackageManager().getInstantAppCookieMaxBytes();
        }

        @TargetApi(26)
        @Override
        public byte[] getInstantAppCookie() {
            return apkContext.getPackageManager().getInstantAppCookie();

        }

        @TargetApi(26)
        @Override
        public void clearInstantAppCookie() {
             apkContext.getPackageManager().clearInstantAppCookie();
        }

        @TargetApi(26)
        @Override
        public void updateInstantAppCookie(byte[] cookie) {
            apkContext.getPackageManager().updateInstantAppCookie(cookie);
        }

        @Override
        public String[] getSystemSharedLibraryNames() {
            return apkContext.getPackageManager().getSystemSharedLibraryNames();
        }
        @TargetApi(26)
        @Override
        public List<SharedLibraryInfo> getSharedLibraries(int flags) {
            return apkContext.getPackageManager().getSharedLibraries(flags);
        }

        @TargetApi(26)
        @Override
        public ChangedPackages getChangedPackages(int sequenceNumber) {
            return apkContext.getPackageManager().getChangedPackages(sequenceNumber);
        }

        @Override
        public FeatureInfo[] getSystemAvailableFeatures() {
            return apkContext.getPackageManager().getSystemAvailableFeatures();
        }

        @Override
        public boolean hasSystemFeature(String name) {
            return apkContext.getPackageManager().hasSystemFeature(name);
        }

        @TargetApi(24)
        @Override
        public boolean hasSystemFeature(String name, int version) {
            return apkContext.getPackageManager().hasSystemFeature(name,version);
        }

        @Override
        public ResolveInfo resolveActivity(Intent intent, int flags) {
            return apkContext.getPackageManager().resolveActivity(intent,flags);
        }

        @Override
        public List<ResolveInfo> queryIntentActivities(Intent intent, int flags) {
            return apkContext.getPackageManager().queryIntentActivities(intent,flags);
        }

        @Override
        public List<ResolveInfo> queryIntentActivityOptions( ComponentName caller, Intent[] specifics, Intent intent, int flags) {
            return apkContext.getPackageManager().queryIntentActivityOptions(caller,specifics,intent,flags);
        }

        @Override
        public List<ResolveInfo> queryBroadcastReceivers(Intent intent, int flags) {
            return apkContext.getPackageManager().queryBroadcastReceivers(intent,flags);
        }

        @Override
        public ResolveInfo resolveService(Intent intent, int flags) {
            return apkContext.getPackageManager().resolveService(intent,flags);
        }

        @Override
        public List<ResolveInfo> queryIntentServices(Intent intent, int flags) {
            return apkContext.getPackageManager().queryIntentServices(intent,flags);
        }

        @TargetApi(19)
        @Override
        public List<ResolveInfo> queryIntentContentProviders(Intent intent, int flags) {
            return apkContext.getPackageManager().queryIntentContentProviders(intent,flags);
        }

        @Override
        public ProviderInfo resolveContentProvider(String name, int flags) {
            return apkContext.getPackageManager().resolveContentProvider(name,flags);
        }

        @Override
        public List<ProviderInfo> queryContentProviders(String processName, int uid, int flags) {
            return apkContext.getPackageManager().queryContentProviders(processName,uid,flags);
        }

        @Override
        public InstrumentationInfo getInstrumentationInfo(ComponentName className, int flags) throws NameNotFoundException {
            return apkContext.getPackageManager().getInstrumentationInfo(className,flags);
        }

        @Override
        public List<InstrumentationInfo> queryInstrumentation(String targetPackage, int flags) {
            return apkContext.getPackageManager().queryInstrumentation(targetPackage,flags);
        }

        @Override
        public Drawable getDrawable(String packageName, int resid, ApplicationInfo appInfo) {
            return apkContext.getPackageManager().getDrawable(packageName,resid,appInfo);
        }

        @Override
        public Drawable getActivityIcon(ComponentName activityName) throws NameNotFoundException {
            return apkContext.getPackageManager().getActivityIcon(activityName);
        }

        @Override
        public Drawable getActivityIcon(Intent intent) throws NameNotFoundException {
            return apkContext.getPackageManager().getActivityIcon(intent);
        }

        @TargetApi(20)
        @Override
        public Drawable getActivityBanner(ComponentName activityName) throws NameNotFoundException {
            return apkContext.getPackageManager().getActivityBanner(activityName);
        }

        @TargetApi(20)
        @Override
        public Drawable getActivityBanner(Intent intent) throws NameNotFoundException {
            return apkContext.getPackageManager().getActivityBanner(intent);
        }

        @Override
        public Drawable getDefaultActivityIcon() {
            return apkContext.getPackageManager().getDefaultActivityIcon();
        }

        @Override
        public Drawable getApplicationIcon(ApplicationInfo info) {
            return apkContext.getPackageManager().getApplicationIcon(info);
        }

        @Override
        public Drawable getApplicationIcon(String packageName) throws NameNotFoundException {
            return apkContext.getPackageManager().getApplicationIcon(packageName);
        }

        @TargetApi(20)
        @Override
        public Drawable getApplicationBanner(ApplicationInfo info) {
            return apkContext.getPackageManager().getApplicationBanner(info);
        }
        @TargetApi(20)
        @Override
        public Drawable getApplicationBanner(String packageName) throws NameNotFoundException {
            return apkContext.getPackageManager().getApplicationBanner(packageName);
        }

        @Override
        public Drawable getActivityLogo(ComponentName activityName) throws NameNotFoundException {
            return apkContext.getPackageManager().getActivityLogo(activityName);
        }

        @Override
        public Drawable getActivityLogo(Intent intent) throws NameNotFoundException {
            return apkContext.getPackageManager().getActivityLogo(intent);
        }

        @Override
        public Drawable getApplicationLogo(ApplicationInfo info) {
            return apkContext.getPackageManager().getApplicationLogo(info);
        }

        @Override
        public Drawable getApplicationLogo(String packageName) throws NameNotFoundException {
            return apkContext.getPackageManager().getApplicationLogo(packageName);
        }

        @TargetApi(21)
        @Override
        public Drawable getUserBadgedIcon(Drawable icon, UserHandle user) {
            return apkContext.getPackageManager().getUserBadgedIcon(icon,user);
        }
        @TargetApi(21)
        @Override
        public Drawable getUserBadgedDrawableForDensity(Drawable drawable, UserHandle user, Rect badgeLocation, int badgeDensity) {
            return apkContext.getPackageManager().getUserBadgedDrawableForDensity(drawable,user,badgeLocation,badgeDensity);
        }
        @TargetApi(21)
        @Override
        public CharSequence getUserBadgedLabel(CharSequence label, UserHandle user) {
            return apkContext.getPackageManager().getUserBadgedLabel(label,user);
        }

        @Override
        public CharSequence getText(String packageName, int resid, ApplicationInfo appInfo) {
            return apkContext.getPackageManager().getText(packageName,resid,appInfo);
        }

        @Override
        public XmlResourceParser getXml(String packageName, int resid, ApplicationInfo appInfo) {
            return apkContext.getPackageManager().getXml(packageName,resid,appInfo);
        }

        @Override
        public CharSequence getApplicationLabel(ApplicationInfo info) {
            return apkContext.getPackageManager().getApplicationLabel(info);
        }

        @Override
        public Resources getResourcesForActivity(ComponentName activityName) throws NameNotFoundException {
            return apkContext.getPackageManager().getResourcesForActivity(activityName);
        }

        @Override
        public Resources getResourcesForApplication(ApplicationInfo app) throws NameNotFoundException {
            return apkContext.getPackageManager().getResourcesForApplication(app);
        }

        @Override
        public Resources getResourcesForApplication(String appPackageName) throws NameNotFoundException {
            return apkContext.getPackageManager().getResourcesForApplication(appPackageName);
        }

        @Override
        public void verifyPendingInstall(int id, int verificationCode) {
             apkContext.getPackageManager().verifyPendingInstall(id,verificationCode);
        }

        @TargetApi(17)
        @Override
        public void extendVerificationTimeout(int id, int verificationCodeAtTimeout, long millisecondsToDelay) {
            apkContext.getPackageManager().extendVerificationTimeout(id,verificationCodeAtTimeout,millisecondsToDelay);
        }

        @Override
        public void setInstallerPackageName(String targetPackage, String installerPackageName) {
            apkContext.getPackageManager().setInstallerPackageName(targetPackage,installerPackageName);
        }

        @Override
        public String getInstallerPackageName(String packageName) {
           return apkContext.getPackageManager().getInstallerPackageName(packageName);
        }

        @Override
        public void addPackageToPreferred(String packageName) {
             apkContext.getPackageManager().addPackageToPreferred(packageName);
        }

        @Override
        public void removePackageFromPreferred(String packageName) {
            apkContext.getPackageManager().removePackageFromPreferred(packageName);
        }

        @Override
        public List<PackageInfo> getPreferredPackages(int flags) {
            return  apkContext.getPackageManager().getPreferredPackages(flags);
        }

        @Override
        public void addPreferredActivity(IntentFilter filter, int match, ComponentName[] set, ComponentName activity) {
            apkContext.getPackageManager().addPreferredActivity(filter,match,set,activity);
        }

        @Override
        public void clearPackagePreferredActivities(String packageName) {
            apkContext.getPackageManager().clearPackagePreferredActivities(packageName);
        }

        @Override
        public int getPreferredActivities( List<IntentFilter> outFilters, List<ComponentName> outActivities, String packageName) {
            return apkContext.getPackageManager().getPreferredActivities(outFilters,outActivities,packageName);
        }

        @Override
        public void setComponentEnabledSetting( ComponentName componentName, int newState, int flags) {
             apkContext.getPackageManager().setComponentEnabledSetting(componentName,newState,flags);
        }

        @Override
        public int getComponentEnabledSetting(ComponentName componentName) {
            return apkContext.getPackageManager().getComponentEnabledSetting(componentName);
        }

        @Override
        public void setApplicationEnabledSetting(String packageName, int newState, int flags) {
            packageName =  packageNameContext.getPackageName().equals(packageName)
                    ?
                    apkContext.getPackageName()
                    :
                    packageName;
            apkContext.getPackageManager().setApplicationEnabledSetting(packageName,newState,flags);
        }

        @Override
        public int getApplicationEnabledSetting(String packageName) {
            packageName =  packageNameContext.getPackageName().equals(packageName)
                    ?
                    apkContext.getPackageName()
                    :
                    packageName;
            return apkContext.getPackageManager().getApplicationEnabledSetting(packageName);
        }

        @Override
        public boolean isSafeMode() {
            return apkContext.getPackageManager().isSafeMode();
        }

        @TargetApi(26)
        @Override
        public void setApplicationCategoryHint( String packageName, int categoryHint) {
            packageName =  packageNameContext.getPackageName().equals(packageName)
                    ?
                    apkContext.getPackageName()
                    :
                    packageName;
             apkContext.getPackageManager().setApplicationCategoryHint(packageName,categoryHint);
        }
        @TargetApi(21)
        @Override
        public PackageInstaller getPackageInstaller() {
          return   apkContext.getPackageManager().getPackageInstaller();
        }
        @TargetApi(26)
        @Override
        public boolean canRequestPackageInstalls() {
            return   apkContext.getPackageManager().canRequestPackageInstalls();
        }
    }

}
