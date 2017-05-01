package ${packageName};

import activitystarter.MakeActivityStarter
import android.os.Bundle
import ${applicationPackage}.R;
import ${applicationPackage}.presentation.common.Presenter
import ${applicationPackage}.presentation.common.PresenterBaseActivity

@MakeActivityStarter
class ${activityClass}Activity : PresenterBaseActivity(), ${activityClass}View {

    override val presenter: Presenter by lazy { ${activityClass}Presenter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.${layoutName})
    }
}