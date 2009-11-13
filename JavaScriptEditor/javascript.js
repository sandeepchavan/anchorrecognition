function calculate(stack) {
    importPackage(javax.swing);
    var optionPane = JOptionPane.showMessageDialog(null, "aaaaaaaaaaaaaaaaaaa");
    var SwingGui = new JavaImporter(javax.swing,
        javax.swing.event,
        javax.swing.border,
        java.awt.event);
    with (SwingGui) {
        // within this 'with' statement, we can access Swing and AWT
        // classes by unqualified (simple) names.

        var mybutton = new JButton("test");
        var myframe = new JFrame("test");
        myframe.setSize(100, 100);
        myframe.add(mybutton);

        myframe.setVisible(true);
        }
    return 10;

    var monthlyPayment = Number.NaN;
    var size = stack.size();
    if (size >= 3) {
        var years = Number(stack.pop());
        var annualInterest  = Number(stack.pop());
        var principal = Number(stack.pop());

        var monthlyInterest = annualInterest / 100 / 12;
        var numberOfPayments = years * 12;
        var x = Math.pow(1+monthlyInterest, numberOfPayments);

        monthlyPayment = (principal*x*monthlyInterest)/(x-1);

        if (!isNaN(monthlyPayment) &&
            (monthlyPayment != Number.POSITIVE_INFINITY) &&
            (monthlyPayment != Number.NEGATIVE_INFINITY)) {

            monthlyPayment = Math.round(monthlyPayment*100)/100;
            stack.push(monthlyPayment);
        } else {
            println("monthlyPayment calc error!");
            if (isNaN(monthlyPayment)) {
                println("monthlyPayment isNaN");
            }
            if (monthlyPayment == Number.POSITIVE_INFINITY) {
                println("monthlyPayment is POSITIVE_INFINITY");
            }
            if (monthlyPayment == Number.NEGATIVE_INFINITY) {
                println("monthlyPayment is NEGATIVE_INFINITY");
            }

            stack.push(annualInterest);
            stack.push(principal);
            stack.push(years);
            monthlyPayment = Number.NaN;
        }
    }
    return monthlyPayment;
}
