module week10assessment {
	requires javafx.controls;
	requires javafx.base;
	requires java.sql;
	
	opens fpoua.week10.solution to javafx.graphics, javafx.base;
}
