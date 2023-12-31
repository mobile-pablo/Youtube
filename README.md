# <center><img src= "https://freepngimg.com/download/youtube/77699-classic-icons-youtube-computer-logo-icon.png" style="width:30px; height:30px" /> Youtube </center>

## <center>TV - oriented Clone of Youtube with their API </center>

## 🧪 60 - 30 - 10 design

<table border="1">
<thead>
  <tr>
    <th>Light mode</th>
    <th>Dark mode</th>
  </tr>
</thead>
<tbody>
  <tr>
    <td><img  src="readme_assets/loading_screen_light.png" /></td>
    <td><img  src="readme_assets/loading_screen_dark.png" /></td>
  </tr>
  <tr>
    <td><img  src="readme_assets/loaded_popular_screen_light.png" /></td>
    <td><img  src="readme_assets/loaded_popular_screen_dark.png" /></td>
  </tr>
<tr>
    <td><img  src="readme_assets/player_screen_light.png"  /></td>
    <td><img  src="readme_assets/player_screen_dark.png" /></td>
</tr>
</tbody>
</table>

## 🪚 Status

![Progress](https://progress-bar.dev/100/?title=v1.0.0&width=200&color=3A5A40)
    
    App have features : 
        * Trending Videos
        * Search with Query history

    App works perfectly but There is one issue (not on my side).
    Youtube API doesnt support any views only iframe which is not so much supported by Android TV.
    I can like open video and play it but not so much add my own features like comment or like.
    That cuts many of future features that I would implement.
    Only few external like share and title with redirrection to Youtube App.
    I have decided to cut external app opening but I wont  be able to remove Share button.

[Iframe issue](https://stackoverflow.com/questions/9640265/problems-giving-focus-to-an-iframe-on-smart-tv)
I was regarding to

## Test coverage 🎯​ ![Percentage](https://progress-bar.dev/90/?width=200&color=ABB49B)

     Almost all classes are covered (1.0.0 status)
     Excluding generated files, mappers and models etc.

## 📱 How to run

1. Install Android Studio according
   to [tutorial](https://developer.android.com/studio?gclid=CjwKCAjwnOipBhBQEiwACyGLukOqCPF7rjbRbw2zo-hldBEOSCVk0P0B1bFUCJjxdKyi6zGc3daUzhoCwSkQAvD_BwE&gclsrc=aw.ds)
2. Download necessary SDK tools
3. Configure Android emulator or connect external device
4. Clone repository from button ' <> Code '
5. Remove ' include(":playground") ' from settings.gradle.kts
   * I added Playground module ( as ignored ) for experimental libraries to test out.
6. Recreate NetworkConst.kt file with Your API key

       package com.mobile.pablo.networking.const

       object NetworkConst {
       const val YOUTUBE_KEY = "Get key from https://developers.google.com/youtube/v3/getting-started"
       }

7. Sync project with Gradle
8. Click an build button : )

## ⚙️ Config

Config files based on Kotlin DSL. <br/>
Dependencies in TOML file <br/>
They are updated via plugin [version-catalog-update-plugin](https://github.com/littlerobots/version-catalog-update-plugin) <br/>
To update run a command

     ./gradlew versionCatalogUpdate

<br/>
I added also a Kover for Unit test Coverage <br/>
To check coverage run :

    ./gradlew koverVerifyDebug koverXmlReportDebug

<br/>
To fix lint issues run : 

    ./gradlew ktLintFormat

## 🤔​ Planning

Jira
at [Youtube Jira](https://mobile-pablo.atlassian.net/jira/software/c/projects/YTV/boards/5) <br/>
Qase at [Youtube Qase](https://app.qase.io/project/YTC) <br/>

## 🛠️​ Tech stack

<div style="width: 100%;">
  <img src="readme_assets/tech_stack.svg" style="width: 100%" />
</div>

## 📸 Screenshots

<h3><b>CI/CD</b></h3>
<table>
<tbody>
  <tr>
    <th><b>CI</b> <i>(primary)</i></th>
    <th><img  src="readme_assets/bitrise_ci_primary.png" /></th>
  </tr>
  <tr>
    <th><b>CD</b> <i>(staging)</i></th>
    <td><img  src="readme_assets/bitrise_cd_staging.png" /> </td>
  </tr>
  <tr>
    <th><b>CD</b> <i>(deploy)</i></th>
    <td><div> <img  src="readme_assets/bitrise_cd_deploy.png" /> </div></td>
  </tr>
</tbody>
</table>

<br/><br/>

<h3><b>Config</b></h3>
<table>
<tbody>
  <tr>
    <th>Firebase App Distribution</th>
    <th><img  src="readme_assets/firebase_app_distribution.png" /></th>
  </tr>
  <tr>
    <th>Jira</th>
    <td><img  src="readme_assets/jira_kanban.png" /></td>
  </tr>
  <tr>
    <th>Crashlytics</th>
    <td><img  src="readme_assets/crashlytics.png" /></td>
  </tr>
  <tr>
    <th>Qase (to be improved)</th>
    <td><img  src="readme_assets/qase_suites.png" /></td>
  </tr>
 <tr>
    <th>Kover - Unit tests coverage</th>
    <td><img  src="readme_assets/kover_coverage.png" /></td>
  </tr>
</tbody>
</table>

## 🙊​ Google Play - Status

    App new releases only will be internal for Youtube Privacy Law.
    Non less it still uses CD - Deploy workflow only not for production

## ​🔗​ To be added (1.0.1)

* Add Playlists
* Add Settings
* Add Profile Screen

## 🫧​ Design

I implemented and used 60 - 30 - 10 rule for this design
<br/><br/>

<img  width = "50%" src="https://cdn.dribbble.com/users/2248264/screenshots/4552223/media/5ee3ff95aa8d67a446556a3ff15e8001.jpg?resize=800x600&vertical=center"/> <br/>

Implementation based
on [Youtube TV Design](https://dribbble.com/shots/4552223-YouTube-TV-App-Redsign)
