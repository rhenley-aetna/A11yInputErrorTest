# A11yInputErrorTest
Test options for handling input field errors so all announcements occur.

When a form is submitted and returns an error, if the submit button is disabled and the 
input field in error is cleared, then keyboard focus shifts to the input field.
However, accessibility focus does not shift, but remains on the disabled input field.

Clearly, it's important to announce that the focus has changed.
But it's also important to announce the error message text.
If both happen, it is possible for one announcement to override the other, 
particularly the focus shift announcement, since it is given high priority.

Two approaches to the problem are shown in this sample:
1. Not shifting the accessibility focus and using an accessibilityLiveRegion to announce the error message.
2. Shifting the accessibility focus, then posting a change to the error message accessibilityLiveRegion. This approach seems to sequence events properly and achieve both objectives.

![Screenshot_20220216-095904 A11yInputErrorTest](https://user-images.githubusercontent.com/85311885/154292806-f35c228c-125e-4aed-b9b2-b2d7bfb00e83.png)
