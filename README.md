# annutils
项目依赖

Add it in your root build.gradle at the end of repositories:

	allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	}
	
Step 2. Add the dependency

	dependencies {
	        compile 'com.github.wslblb:annutils:1.0'
	}

用法

在属性上加@BindField（ID）
然后在setContentView调用后，属性使用前调用BIndViews.bind(this)
