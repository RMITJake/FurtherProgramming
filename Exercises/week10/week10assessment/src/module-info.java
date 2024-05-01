module week10assessment {
	requires javafx.controls;
	requires javafx.base;
	requires java.sql;
	
	opens fpoua.week10.assessment to javafx.graphics, javafx.base;
}
