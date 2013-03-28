mvn install:install-file -Dfile=libGoogleAnalyticsV2.jar -DgroupId=com.google -DartifactId=analytics -Dversion=2.0b4 -Dpackaging=jar

mvn deploy:deploy-file -DgroupId=com.google \
  -DartifactId=analytics \
  -Dversion=2.0b4 \
  -Dpackaging=jar \
  -Dfile=analytics-googleV2/libs/libGoogleAnalyticsV2.jar \
  -DrepositoryId=repo1 \
  -DgeneratePom=false \
  -Durl=dav:https://repository-jsy.forge.cloudbees.com/release/

mvn deploy:deploy-file -DgroupId=com.google \
  -DartifactId=analytics \
  -Dversion=1.0 \
  -Dpackaging=jar \
  -Dfile=analytics-google/libs/libGoogleAnalytics.jar \
  -DrepositoryId=repo1 \
  -DgeneratePom=false \
  -Durl=dav:https://repository-jsy.forge.cloudbees.com/release/
  
mvn deploy:deploy-file -DgroupId=com.flurry \
  -DartifactId=FlurryAgent \
  -Dversion=3.1.0 \
  -Dpackaging=jar \
  -Dfile=analytics-flurry/libs/FlurryAgent-3.1.0.jar \
  -DrepositoryId=repo1 \
  -DgeneratePom=false \
  -Durl=dav:https://repository-jsy.forge.cloudbees.com/release/