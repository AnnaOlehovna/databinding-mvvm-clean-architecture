package anna.poddubnaya.presentation.base;


public abstract class BaseItemViewModel<Model> {

   //position на случай если надо знать позицию в recycleView
   public abstract void setItem(Model model, int position);

   public void init(){

   }

   public void release(){

   }

}
