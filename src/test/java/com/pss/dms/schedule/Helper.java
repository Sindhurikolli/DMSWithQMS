package com.pss.dms.schedule;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class Helper {

	public static Select getSelectInstance(WebDriver driver, By byObjForDropDownList) {
		WebElement webElementForSelect = driver.findElement(byObjForDropDownList);
		Select dropDownList = new Select(webElementForSelect);
		return dropDownList;
	}

	public static boolean selectingSingleApprovalRecord(WebDriver driver, String selectingUserSingleApproval,
			boolean isRecordSelected, int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("usersTableContainer"));// Single Selection User Table
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
//            String a = driver.findElement(By.className("jtable-page-info")).getText();// For Ex: Showing 1-1 of 1
			String a = driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/div[4]/div[2]/span"))
					.getText();
			// For Ex: Showing 1-1 of 1

			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1)
					&& ((selectingUserSingleApproval == null) || ("".equalsIgnoreCase(selectingUserSingleApproval)))) {
				selectingUserSingleApproval = driver
						.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[1]/td[1]"))
						.getText();
			} else if (selectingUserSingleApproval == null || ("".equalsIgnoreCase(selectingUserSingleApproval))) {
				selectingUserSingleApproval = driver
						.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]")).getText();
			}
			++count;
		}

		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String selectingUser = driver
								.findElement(By
										.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[" + i + "]/td[1]"))
								.getText();
						if (selectingUserSingleApproval.equalsIgnoreCase(selectingUser)) {
							driver.findElement(
									By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr[" + i + "]/td[1]"))
									.click();
							isRecordSelected = true;
							break;
						}
						if (isRecordSelected) {
							break;
						}
					}
				} else {
					String selectingUser = driver
							.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]"))
							.getText();
					if (selectingUserSingleApproval.equalsIgnoreCase(selectingUser)) {
						driver.findElement(By.xpath("//*[@id=\"usersTableContainer\"]/div/table/tbody/tr/td[1]"))
								.click();
						isRecordSelected = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelected) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#usersTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in single approval
					Thread.sleep(8000);
					table = driver.findElement(By.id("usersTableContainer"));// single approval table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}
		return isRecordSelected;
	}

	// Selecting Single Items in Item Table(For the menu Item Questions)
//	public static boolean selectRecordFromItemDetails(WebDriver driver, String documentNo,
//			boolean isRecordSelectedForItems, int count) throws InterruptedException {
//		WebElement table = driver.findElement(By.id("MultiItemTableContainer"));
//		WebElement tableBody = table.findElement(By.tagName("tbody"));
//		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//		int totalNoOfRecords = 0;
//		int noOfRecordsChecked = 0;
//		if (perPageNoOfRecordsPresent > 0) {
//			String a = driver.findElement(By.xpath("//*[@id=\"MultiItemTableContainer\"]/div/div[4]/div[2]/span"))
//					.getText();// For Ex: Showing 1-1 of 1
//			String[] parts = a.split(" of ");
//			totalNoOfRecords = Integer.parseInt(parts[1].trim());
//		}
//		if (perPageNoOfRecordsPresent > 0 && count == 0) {
//			if ((totalNoOfRecords > 1) && ((documentNo == null) || ("".equalsIgnoreCase(documentNo)))) {
//				String docWithVersion = driver
//						.findElement(By.xpath("//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr[1]/td[3]"))
//						.getText();// Doc No
//				String[] getDocNo = docWithVersion.split("-");
//				documentNo = getDocNo[0];
//			} else if ((documentNo == null) || ("".equalsIgnoreCase(documentNo))) {
//				String docWithVersion = driver
//						.findElement(By.xpath("//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr/td[3]"))
//						.getText();// Doc No
//				String[] getDocNo = docWithVersion.split("-");
//				documentNo = getDocNo[0];
//			}
//			++count;
//		}
//		if (perPageNoOfRecordsPresent > 0) {
//			while (noOfRecordsChecked < totalNoOfRecords) {
//				if (totalNoOfRecords > 1) {
//					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
//						String docWithVersion = driver
//								.findElement(By.xpath(
//										"//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr[" + i + "]/td[3]"))
//								.getText();// Doc No
//						String[] getDocNo = docWithVersion.split("-");
//						String documentNumber = getDocNo[0];
//						if (documentNo.equalsIgnoreCase(documentNumber)) {
//							driver.findElement(By
//									.xpath("//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr[" + i + "]/td[3]"))
//									.click();
//							isRecordSelectedForItems = true;
//							break;
//						}
//					}
//					if (isRecordSelectedForItems) {
//						break;
//					}
//				} else {
//					String docWithVersion = driver
//							.findElement(By.xpath("//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr/td[3]"))
//							.getText();
//					String[] getDocNo = docWithVersion.split("-");
//					String documentNumber = getDocNo[0];
//					if (documentNo.equalsIgnoreCase(documentNumber)) {
//						driver.findElement(By.xpath("//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr/td[3]"))
//								.click();
//						isRecordSelectedForItems = true;
//						break;
//					}
//				}
//				noOfRecordsChecked += perPageNoOfRecordsPresent;
//				if ((!isRecordSelectedForItems) && (noOfRecordsChecked < totalNoOfRecords)) {
//					driver.findElement(By.className("jtable-page-number-next")).click();// next page in Item table
//					Thread.sleep(3000);
//					table = driver.findElement(By.id("MultiItemTableContainer"));// Item Details table
//					tableBody = table.findElement(By.tagName("tbody"));
//					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//				}
//			}
//		}
//		return isRecordSelectedForItems;
//	}

	// Selecting Multi Items in Table(For Schedules)
	public static boolean selectMultiItemDetails(WebDriver driver, String multiDocNoForItem,
			boolean isRecordSelectedForMultiItems, int count2) throws InterruptedException {
		WebElement table = driver.findElement(By.id("MultiItemTableContainer"));
		WebElement tableBody = driver.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"MultiItemTableContainer\"]/div/div[4]/div[2]/span"))
					.getText();
			String[] parts = a.split(" of ");
			try {
				totalNoOfRecords = Integer.parseInt(parts[1].trim());
//			System.out.println(totalNoOfRecords);
			} catch (Exception e) {
				System.out.println(e.getMessage());
			}
		}
		if (perPageNoOfRecordsPresent > 0 && count2 == 0) {
			if ((totalNoOfRecords > 1) && ((multiDocNoForItem == null) || ("".equalsIgnoreCase(multiDocNoForItem)))) {
				multiDocNoForItem = driver
						.findElement(By.xpath("//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr[1]/td[5]"))
						.getText();// WorkFlow Name

			} else if ((multiDocNoForItem == null) || ("".equalsIgnoreCase(multiDocNoForItem))) {
				multiDocNoForItem = driver
						.findElement(By.xpath("//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr/td[5]"))
						.getText();// WorkFlow
				// Name
			}
			++count2;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String LMSWFName = driver
								.findElement(By.xpath(
										"//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr[ " + i + "]/td[5]"))
								.getText();// WorkFlow Name
//						System.out.println(LMSWFName);
						if (multiDocNoForItem.contains(LMSWFName)) {
							driver.findElement(By
									.xpath("//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr[" + i + "]/td[5]"))
									.click();
							isRecordSelectedForMultiItems = true;
							break;
						}
					}
					if (isRecordSelectedForMultiItems) {
						break;
					}
				} else {
					String LMSWFName = driver
							.findElement(By.xpath("//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr/td[5]"))
							.getText();
					if (multiDocNoForItem.contains(LMSWFName)) {
						driver.findElement(By.xpath("//*[@id=\"MultiItemTableContainer\"]/div/table/tbody/tr/td[5]"))
								.click();
						isRecordSelectedForMultiItems = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForMultiItems) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in New Document
																						// Details table
					Thread.sleep(7000);
					table = driver.findElement(By.id("MultiItemTableContainer"));// New Document Details table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}

			}
		}
		return isRecordSelectedForMultiItems;
	}

	// Selecting WorkFlow(Create Curricula)
	public static boolean selectRecordForWorkFlow(WebDriver driver, String workFlowName, boolean isRecordSelectedForWF,
			int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("workFlowJtable"));
		WebElement tableBody = driver.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"workFlowJtable\"]/div/div[4]/div[2]/span")).getText();
			String[] parts = a.split(" of ");
			totalNoOfRecords = Integer.parseInt(parts[1].trim());
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((workFlowName == null) || ("".equalsIgnoreCase(workFlowName)))) {
				workFlowName = driver
						.findElement(By.xpath("//*[@id=\"workFlowJtable\"]/div/div[4]/table/tbody/tr[1]/td[1]"))
						.getText();// WorkFlow Name

			} else if ((workFlowName == null) || ("".equalsIgnoreCase(workFlowName))) {
				workFlowName = driver
						.findElement(By.xpath("//*[@id=\"workFlowJtable\"]/div/div[4]/table/tbody/tr/td[1]")).getText();// WorkFlow
																														// Name
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String LMSWFName = driver
								.findElement(By.xpath(
										"//*[@id=\"workFlowJtable\"]/div/div[4]/table/tbody/tr[" + i + "]/td[1]"))
								.getText();// WorkFlow Name
						if (workFlowName.contains(LMSWFName)) {
							driver.findElement(
									By.xpath("//*[@id=\"workFlowJtable\"]/div/div[4]/table/tbody/tr[" + i + "]/td[1]"))
									.click();
							isRecordSelectedForWF = true;
							break;
						}
					}
					if (isRecordSelectedForWF) {
						break;
					}
				} else {
					String LMSWFName = driver
							.findElement(By.xpath("//*[@id=\"workFlowJtable\"]/div/div[4]/table/tbody/tr/td[1]"))
							.getText();
					if (workFlowName.contains(LMSWFName)) {
						driver.findElement(By.xpath("//*[@id=\"workFlowJtable\"]/div/div[4]/table/tbody/tr/td[1]"))
								.click();
						isRecordSelectedForWF = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForWF) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in New Document
																						// Details table
					Thread.sleep(3000);
					table = driver.findElement(By.id("workFlowJtable"));// New Document Details table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}

			}
		}
		return isRecordSelectedForWF;
	}

	// Selecting MultiCurricula(Trainer Registration,Modify Trainer,Schedules)
	public static boolean selMultiSelForCurricula(WebDriver driver, String multiCurriculaName,
			boolean isRecordSelectedForMultiCurrTble, int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("MultiCurriculaJTable"));// Multi Curricula Select Table
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"MultiCurriculaJTable\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			totalNoOfRecords = Integer.parseInt(parts[1].trim());
		}

		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((multiCurriculaName == null) || ("".equalsIgnoreCase(multiCurriculaName)))) {
				multiCurriculaName = driver
						.findElement(By.xpath(" //*[@id=\"MultiCurriculaJTable\"]/div/table/tbody/tr[1]/td[3]"))
						.getText();
			} else if ((multiCurriculaName == null) || ("".equalsIgnoreCase(multiCurriculaName))) {
				multiCurriculaName = driver
						.findElement(By.xpath("//*[@id=\"MultiCurriculaJTable\"]/div/table/tbody/tr/td[3]")).getText();
			}
			count++;
		}

		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String curriculaName = driver
								.findElement(By
										.xpath("//*[@id=\"MultiCurriculaJTable\"]/div/table/tbody/tr[" + i + "]/td[3]"))
								.getText();
						if (multiCurriculaName.equalsIgnoreCase(curriculaName)) {
							driver.findElement(
									By.xpath("//*[@id=\"MultiCurriculaJTable\"]/div/table/tbody/tr[" + i + "]/td[3]"))
									.click();
							isRecordSelectedForMultiCurrTble = true;
							break;
						}
					}
					if (isRecordSelectedForMultiCurrTble) {
						break;
					}
				} else {
					String curriculaName = driver
							.findElement(By.xpath(" //*[@id=\"MultiCurriculaJTable\"]/div/table/tbody/tr/td[3]"))
							.getText();
					if (multiCurriculaName.equalsIgnoreCase(curriculaName)) {
						driver.findElement(By.xpath(" //*[@id=\"MultiCurriculaJTable\"]/div/table/tbody/tr/td[3]"))
								.click();
						isRecordSelectedForMultiCurrTble = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForMultiCurrTble) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#MultiCurriculaJTable > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in Curricula Details list
					Thread.sleep(3000);
					table = driver.findElement(By.id("MultiCurriculaJTable"));// Multi Curricula Details table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}

			}
		}
		return isRecordSelectedForMultiCurrTble;
	}

	// Select WorkFlow(Schedules)
	public static boolean selectRecordForWorkFlowForSchedules(WebDriver driver, String workFlowName,
			boolean recSelectedForWF, int count) throws InterruptedException {
		WebElement table = driver.findElement(By.id("workFlowJtable"));
		WebElement tableBody = driver.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
			String a = driver.findElement(By.xpath("//*[@id=\"workFlowJtable\"]/div/div[4]/div[2]/span")).getText();
			String[] parts = a.split(" of ");
			totalNoOfRecords = Integer.parseInt(parts[1].trim());
		}
		if (perPageNoOfRecordsPresent > 0 && count == 0) {
			if ((totalNoOfRecords > 1) && ((workFlowName == null) || ("".equalsIgnoreCase(workFlowName)))) {
				workFlowName = driver.findElement(By.xpath("//*[@id=\"workFlowJtable\"]/div/table/tbody/tr[1]/td[1]"))
						.getText();// WorkFlow Name

			} else if ((workFlowName == null) || ("".equalsIgnoreCase(workFlowName))) {
				workFlowName = driver.findElement(By.xpath("//*[@id=\"workFlowJtable\"]/div/table/tbody/tr/td[1]"))
						.getText();// WorkFlow Name
			}
			++count;
		}
		if (perPageNoOfRecordsPresent > 0) {
			while (noOfRecordsChecked < totalNoOfRecords) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String LMSWFName = driver
								.findElement(
										By.xpath("//*[@id=\"workFlowJtable\"]/div/table/tbody/tr[" + i + "]/td[1]"))
								.getText();// WorkFlow Name
						if (workFlowName.equalsIgnoreCase(LMSWFName)) {
							driver.findElement(
									By.xpath("//*[@id=\"workFlowJtable\"]/div/table/tbody/tr[" + i + "]/td[1]"))
									.click();
							recSelectedForWF = true;
							break;
						}
					}
					if (recSelectedForWF) {
						break;
					}
				} else {
					String LMSWFName = driver
							.findElement(By.xpath("//*[@id=\"workFlowJtable\"]/div/table/tbody/tr/td[1]")).getText();
					if (workFlowName.equalsIgnoreCase(LMSWFName)) {
						driver.findElement(By.xpath("//*[@id=\"workFlowJtable\"]/div/table/tbody/tr/td[1]")).click();
						recSelectedForWF = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!recSelectedForWF) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.className("jtable-page-number-next")).click();// next page in New Document
																						// Details table
					Thread.sleep(3000);
					table = driver.findElement(By.id("workFlowJtable"));// New Document Details table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}

			}
		}
		return recSelectedForWF;
	}

	// Multi Selection Users(Schedules)
//    public static boolean selectingMultiUsersRecordForSchedules(WebDriver driver, String multiUsersInSchedules, boolean isRecordSelectedForSelectingMultiUsers, int count) throws InterruptedException {
//        WebElement table = driver.findElement(By.id("multiUserTableContainer"));
//        WebElement tableBody = table.findElement(By.tagName("tbody"));
//        int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//        int noOfRecordsToBeSelect = 0;
//        int totalNoOfRecords = 0;
//        int noOfRecordsChecked = 0;
//        List<String> selectUsersList1 = new ArrayList<String>();
//        String selectUsersList[] = multiUsersInSchedules.split(",");
//        for (String str : selectUsersList) {
//            selectUsersList1.add(str);
//        }
//        noOfRecordsToBeSelect = selectUsersList.length;
//        if (perPageNoOfRecordsPresent > 0) {
//            String a = driver.findElement(By.xpath("//*[@id=\"multiUserTableContainer\"]/div/div[4]/div[2]/span")).getText();// For Ex: Showing 1-1 of 1
//            String[] parts = a.split(" of ");
//            totalNoOfRecords = Integer.parseInt(parts[1].trim());
//        }
//        if (perPageNoOfRecordsPresent > 0 && count == 0) {
//            if ((totalNoOfRecords > 1) && ((multiUsersInSchedules == null) || ("".equalsIgnoreCase(multiUsersInSchedules)))) {
//                multiUsersInSchedules = driver.findElement(By.xpath("//*[@id=\"multiUserTableContainer\"]/div/table/tbody/tr[1]/td[2]")).getText();
//                selectUsersList[0] = multiUsersInSchedules;
//            } else if ((multiUsersInSchedules == null) || ("".equalsIgnoreCase(multiUsersInSchedules))) {
//                multiUsersInSchedules = driver.findElement(By.xpath("//*[@id=\"multiUserTableContainer\"]/div/table/tbody/tr/td[2]")).getText();
//                selectUsersList[0] = multiUsersInSchedules;
//            }
//            count++;
//        }
//        List<String> selectedUsers = new ArrayList();
//        if (noOfRecordsToBeSelect > 0 && noOfRecordsChecked < totalNoOfRecords) {
//            int j = 0;
//            while (noOfRecordsToBeSelect > 0) {
//                for (j = 0; j < selectUsersList.length; j++) {
//                    String usersList = selectUsersList[j];
////            for (String usersList : selectUsersList) {
//                    noOfRecordsChecked = 0;
//                    if (totalNoOfRecords > 1) {
//                        for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
//                            String username = driver.findElement(By.xpath("//*[@id=\"multiUserTableContainer\"]/div/table/tbody/tr[" + i + "]/td[2]")).getText();
//                            if (usersList.equalsIgnoreCase(username)) {
//                                driver.findElement(By.xpath("//*[@id=\"multiUserTableContainer\"]/div/table/tbody/tr[" + i + "]/td[2]")).click();
////                            selectUsersList1.remove(j);    
//                                selectedUsers.add(usersList);
//                                noOfRecordsToBeSelect--;
//                                if (noOfRecordsToBeSelect == 0) {
//                                    isRecordSelectedForSelectingMultiUsers = true;
//                                }
//                                break;
//                            }
//                        }
//                    } else {
//                        String userName = driver.findElement(By.xpath("//*[@id=\"multiUserTableContainer\"]/div/table/tbody/tr/td[2]")).getText();
//                        if (usersList.equalsIgnoreCase(userName)) {
//                            driver.findElement(By.xpath("//*[@id=\"multiUserTableContainer\"]/div/table/tbody/tr/td[2]")).click();
//                            isRecordSelectedForSelectingMultiUsers = true;
//                            noOfRecordsToBeSelect--;
//                        }
//                    }
//                    usersList = null;
//
//                }
//
//                for (String i : selectedUsers) {
//                    selectUsersList1.remove(i);
//                }
//                noOfRecordsChecked += perPageNoOfRecordsPresent;
//                if ((!isRecordSelectedForSelectingMultiUsers) && (!selectUsersList1.isEmpty()) && (noOfRecordsChecked < totalNoOfRecords)) {
////                if ((!isRecordSelectedForSelectingMultiUsers) && (noOfRecordsChecked < totalNoOfRecords)) {
//                    driver.findElement(By.className("jtable-page-number-next")).click();//next page in multi user table list
//                    Thread.sleep(3000);
//                    table = driver.findElement(By.id("multiUserTableContainer"));//Multi User Details table
//                    tableBody = table.findElement(By.tagName("tbody"));
//                    perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
//                }
//            }
//
//        }
//        return isRecordSelectedForSelectingMultiUsers;
//    }
	// Multi Selection Users(Schedules)
	public static boolean selectingMultiUsersRecordForSchedules(WebDriver driver, String multiUsersInSchedules,
			boolean isRecordSelectedForSelectingMultiUsers, int count1) throws InterruptedException {
		WebElement table = driver.findElement(By.id("multiUserTableContainer"));
		WebElement tableBody = table.findElement(By.tagName("tbody"));
		int perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
		int totalNoOfRecords = 0;
		int noOfRecordsChecked = 0;
		if (perPageNoOfRecordsPresent > 0) {
//            String a = driver.findElement(By.className("jtable-page-info")).getText();// For Ex: Showing 1-1 of 1
			String a = driver.findElement(By.xpath("//*[@id=\"multiUserTableContainer\"]/div/div[4]/div[2]/span"))
					.getText();// For Ex: Showing 1-1 of 1
			String[] parts = a.split(" of ");
			totalNoOfRecords = Integer.parseInt(parts[1].trim());
		}
		if (perPageNoOfRecordsPresent > 0 && count1 == 0) {
			if ((totalNoOfRecords > 1)
					&& ((multiUsersInSchedules == null) || ("".equalsIgnoreCase(multiUsersInSchedules)))) {
				multiUsersInSchedules = driver
						.findElement(By.xpath("//*[@id=\"multiUserTableContainer\"]/div/table/tbody/tr[1]/td[5]"))
						.getText();
			} else if (multiUsersInSchedules == null || ("".equalsIgnoreCase(multiUsersInSchedules))) {
				multiUsersInSchedules = driver
						.findElement(By.xpath("//*[@id=\"multiUserTableContainer\"]/div/table/tbody/tr/td[5]"))
						.getText();
			}
			++count1;
		} // *[@id="multiUserTableContainer"]/div/table/tbody/tr[2]/td[2]

		if (perPageNoOfRecordsPresent > 0) {
			while ((noOfRecordsChecked < totalNoOfRecords) && (!isRecordSelectedForSelectingMultiUsers)) {
				if (totalNoOfRecords > 1) {
					for (int i = 1; i <= perPageNoOfRecordsPresent; i++) {
						String selectingUser = driver
								.findElement(By.xpath(
										"//*[@id=\"multiUserTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[5]"))
								.getText();
						if (multiUsersInSchedules.equalsIgnoreCase(selectingUser)) {
							Thread.sleep(2000);
							driver.findElement(By.xpath(
									"//*[@id=\"multiUserTableContainer\"]/div/table/tbody/tr[ " + i + " ]/td[1]/input"))
									.click();
							isRecordSelectedForSelectingMultiUsers = true;
							break;
						}
						if (isRecordSelectedForSelectingMultiUsers) {
							break;
						}
					}
				} else {
					String selectingUser = driver
							.findElement(By.xpath("//*[@id=\"multiUserTableContainer\"]/div/table/tbody/tr/td[5]"))
							.getText();
					if (multiUsersInSchedules.contains(selectingUser)) {
						Thread.sleep(2000);
						driver.findElement(
								By.xpath("//*[@id=\"multiUserTableContainer\"]/div/table/tbody/tr/td[1]/input"))
								.click();
						isRecordSelectedForSelectingMultiUsers = true;
						break;
					}
				}
				noOfRecordsChecked += perPageNoOfRecordsPresent;
				if ((!isRecordSelectedForSelectingMultiUsers) && (noOfRecordsChecked < totalNoOfRecords)) {
					driver.findElement(By.cssSelector(
							"#multiUserTableContainer > div > div.jtable-bottom-panel > div.jtable-left-area > span.jtable-page-list > span.jtable-page-number-next"))
							.click();// next page in single approval
					Thread.sleep(3000);
					table = driver.findElement(By.id("multiUserTableContainer"));// single approval table
					tableBody = table.findElement(By.tagName("tbody"));
					perPageNoOfRecordsPresent = tableBody.findElements(By.tagName("tr")).size();
				}
			}
		}

		return isRecordSelectedForSelectingMultiUsers;
	}

}
